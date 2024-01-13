package com.bbkk.project.service;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson2.JSONObject;
import com.bbkk.project.constant.LockKeyConstant;
import com.bbkk.project.data.EmqxMqttPublishParams;
import com.bbkk.project.data.EmqxSessionSubscribedParams;
import com.bbkk.project.data.PublishMessageDTO;
import com.bbkk.project.data.common.EmqxWebhookResponse;
import com.bbkk.project.utils.ValidatedUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import static com.bbkk.project.constant.MqTopicConstant.IOT_EMQX_MQTT_PUBLISH_MESSAGE;
import static com.bbkk.project.constant.MqTopicConstant.IOT_EMQX_MQTT_SESSION_SUBSCRIBED;

/**
 * EMQX Webhook 处理接口 业务逻辑
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-21 18:30
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class EmqxWebHookHandlerService {

    private final RocketMQTemplate rocketMQTemplate;
    private final RedissonClient redissonClient;

    public EmqxWebhookResponse publishMessageHandler(EmqxMqttPublishParams params, HttpServletResponse response) {
        PublishMessageDTO publishMessageDTO;
        try {
            // 解析 payload
            publishMessageDTO = JSONObject.parseObject(params.getPayload(), PublishMessageDTO.class);
            // 校验是否缺少字段
            ValidatedUtil.validateEntity(publishMessageDTO);
            if (StringUtils.isBlank(params.getTopic())) {
                throw new NullPointerException("topic is null");
            }
            String[] array = params.getTopic().split("/");
            if (array.length != 3) {
                throw new RuntimeException("topic 格式错误");
            }
            // 设置产品id
            publishMessageDTO.setProductId(Long.parseLong(array[0]));
            // 设置设备id
            publishMessageDTO.setDeviceId(array[1]);
            // 设置行为类型
            publishMessageDTO.setType(array[2]);
        } catch (ConstraintViolationException ex) {
            log.warn("接收消息时,字段校验未通过 原因为: {}", ex.getConstraintViolations().stream().toList().get(0).getMessage());
            response.setStatus(HttpStatus.HTTP_FORBIDDEN);
            return EmqxWebhookResponse.error("The field verification fails.");
        } catch (Exception ex) {
            log.info("解析来自 emqx 发布消息 payload 时产生异常", ex);
            response.setStatus(HttpStatus.HTTP_BAD_REQUEST);
            return EmqxWebhookResponse.error("Request format error.");
        }
        // 把消息发送至 mq
        rocketMQTemplate.asyncSend(IOT_EMQX_MQTT_PUBLISH_MESSAGE, publishMessageDTO, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("Send message successfully, topic={}, messageId={}", IOT_EMQX_MQTT_PUBLISH_MESSAGE, sendResult.getMsgId());
            }

            @Override
            public void onException(Throwable e) {
                log.error("Failed to send message", e);
            }
        });


        return EmqxWebhookResponse.success();
    }

    public EmqxWebhookResponse sessionSubscribedHandler(EmqxSessionSubscribedParams params, HttpServletResponse response) {
        log.info(JSONObject.toJSONString(params));
        /*
        将消息发送至 mq 由 mq 消费者完成:
        1. 查询产品信息 获取物模型、影子等数据
        2. 将查询到的信息 发送至 mq。iotp-bridge 模块中存在对该 topic 的消费者 去响应设备数据
         */
        // 为保证幂等性 加锁 直到 mq 消费完成
        RLock rLock = redissonClient.getLock(LockKeyConstant.DEVICE_SUBSCRIPTION_TOPIC_LOCK.getKey() + params.getTopic());
        // 尝试获取锁
        if (!rLock.tryLock()) {
            // 如果没有拿到锁 那直接不做任何处理
            return EmqxWebhookResponse.success();
        }
        rocketMQTemplate.asyncSend(IOT_EMQX_MQTT_SESSION_SUBSCRIBED, params, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("Send message successfully, topic={}, messageId={}", IOT_EMQX_MQTT_SESSION_SUBSCRIBED, sendResult.getMsgId());
            }

            @Override
            public void onException(Throwable e) {
                log.error("Failed to send message", e);
                // 没有成功的把 消息发送至 mq 则释放锁
                rLock.unlock();
            }
        });

        return EmqxWebhookResponse.success();
    }
}
