package com.bbkk.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024/1/7 14:00
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class IotpDeviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotpDeviceApplication.class, args);
    }

}
