package com.bbkb.project.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * netty mqtt 配置
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-16 12:59
 **/
@Data
@Component
@ConfigurationProperties(prefix = "netty-mqtt")
public class NettyMqttConfig {

    /**
     * mqtt 服务器监听端口号
     */
    private Integer port;

}
