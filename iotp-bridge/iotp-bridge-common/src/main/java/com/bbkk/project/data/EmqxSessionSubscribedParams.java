package com.bbkk.project.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

/**
 * emqx 客户端订阅成功事件
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-23 16:49
 **/
@Data
public class EmqxSessionSubscribedParams {

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
     * SUBSCRIBE Properties (仅适用于 5.0)
     */
    @JsonProperty("sub_props")
    private Map<String, Object> subProps;

    /**
     * 事件触发时间 (单位：毫秒)
     */
    private Long timestamp;

    /**
     * 事件触发所在节点
     */
    private String node;

}
