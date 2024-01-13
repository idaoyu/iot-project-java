package com.bbkk.project.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * OpenFeign 配置
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-12 21:48
 **/
@Configuration
@EnableFeignClients(basePackages = {"com.bbkk.project.api"})
public class OpenFeignConfig {
}
