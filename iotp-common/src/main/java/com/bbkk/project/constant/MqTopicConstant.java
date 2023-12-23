package com.bbkk.project.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * mq topic 枚举
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-23 14:25
 **/
@Getter
@AllArgsConstructor
public enum MqTopicConstant {

    /**
     * 物联网 emqx mqtt 发布消息
     */
    IOT_EMQX_MQTT_PUBLISH_MESSAGE("iot_emqx_mqtt_publish_message"),
    /**
     * emqx mqtt 客户端订阅成功
     */
    IOT_EMQX_MQTT_SESSION_SUBSCRIBED("iot_emqx_mqtt_session_subscribed"),
    ;

    private final String topic;

}
