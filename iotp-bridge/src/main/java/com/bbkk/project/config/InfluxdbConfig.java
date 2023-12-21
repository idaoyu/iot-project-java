package com.bbkk.project.config;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * influx db 配置
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-21 18:33
 **/
@Data
@Component
@ConfigurationProperties(prefix = "influx")
public class InfluxdbConfig {

    /**
     * influxdb 服务器地址
     */
    private String url;

    /**
     * influxdb 组织
     */
    private String organization;

    /**
     * 存储桶
     */
    private String bucket;

    /**
     * 访问token
     */
    private String token;

    @Bean
    public InfluxDBClient influxDbClient() {
        return InfluxDBClientFactory.create(
                this.url,
                this.token.toCharArray()
        );
    }

}
