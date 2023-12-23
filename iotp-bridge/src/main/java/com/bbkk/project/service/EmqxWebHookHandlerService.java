package com.bbkk.project.service;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson2.JSONObject;
import com.bbkk.project.constant.MqTopicConstant;
import com.bbkk.project.data.EmqxWebhookMqttParams;
import com.bbkk.project.data.PublishMessageDTO;
import com.bbkk.project.data.common.EmqxWebhookResponse;
import com.bbkk.project.utils.ValidatedUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

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

    public EmqxWebhookResponse publishMessageHandler(EmqxWebhookMqttParams params, HttpServletResponse response) {
        PublishMessageDTO publishMessageDTO;
        try {
            // 解析 payload
            publishMessageDTO = JSONObject.parseObject(params.getPayload(), PublishMessageDTO.class);
            // 校验是否缺少字段
            ValidatedUtil.validateEntity(publishMessageDTO);
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
        rocketMQTemplate.asyncSend(MqTopicConstant.IOT_EMQX_MQTT_PUBLISH_MESSAGE.getTopic(), publishMessageDTO, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("Send message successfully, messageId={}", sendResult.getMsgId());
            }

            @Override
            public void onException(Throwable e) {
                log.error("Failed to send message", e);
            }
        });


        return EmqxWebhookResponse.success();
    }
}
