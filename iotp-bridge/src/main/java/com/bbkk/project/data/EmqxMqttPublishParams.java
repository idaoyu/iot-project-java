package com.bbkk.project.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

/**
 * emqx mqtt 发布消息
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-21 18:14
 **/
@Data
public class EmqxMqttPublishParams {

    /**
     * MQTT 消息 ID
     */
    private String id;

    /**
     * 消息来源 Client ID
     */
    @JsonProperty("clientid")
    private String clientId;

    /**
     * 消息来源用户名
     */
    private String username;

    /**
     * MQTT 消息体
     */
    private String payload;

    /**
     * 客户端的 IPAddress
     */
    @JsonProperty("peerhost")
    private String peerHost;

    /**
     * MQTT 主题
     */
    private String topic;

    /**
     * MQTT 消息的 QoS
     */
    private Integer qos;

    /**
     * MQTT 消息的 Flags
     */
    private Map<String, Object> flags;

    /**
     * PUBLISH Properties (仅适用于 MQTT 5.0)
     */
    @JsonProperty("pub_props")
    private Map<String, Object> pubProps;

    /**
     * 事件触发时间 (单位：毫秒)
     */
    private Long timestamp;

    /**
     * PUBLISH 消息到达 Broker 的时间 (单位：毫秒)
     */
    @JsonProperty("publish_received_at")
    private Long publishReceivedAt;

    /**
     * 事件触发所在节点
     */
    private String node;

}
