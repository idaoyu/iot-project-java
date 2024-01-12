package com.bbkk.project.consumer.emqx;

import com.alibaba.fastjson2.JSONObject;
import com.bbkk.project.constant.MqTopicConstant;
import com.bbkk.project.data.PublishMessageDTO;
import com.bbkk.project.service.IotEmqxMqttPublishMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * emqx mqtt 发布消息消费者
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-24 11:51
 **/
@Slf4j
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(topic = MqTopicConstant.IOT_EMQX_MQTT_PUBLISH_MESSAGE, consumerGroup = "emqx_message_consumer")
public class IotEmqxMqttPublishMessageConsumer implements RocketMQListener<PublishMessageDTO> {

    private final IotEmqxMqttPublishMessageService iotEmqxMqttPublishMessageService;

    @Override
    public void onMessage(PublishMessageDTO message) {
        log.info("消费者收到消息: {}", JSONObject.toJSONString(message));
        iotEmqxMqttPublishMessageService.handlerMessage(message);
    }
}
