package com.bbkk.project.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;

/**
 * 统一定义的 redis 缓存 key
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-29 20:11
 **/
@Getter
@AllArgsConstructor
public enum RedisCacheKeyConstant {

    /**
     * 存放系统中全部的角色以及对应的权限的集合
     */
    ALL_ROLE_PERMISSION_LIST("upms:all_role_permission_list", Duration.ofDays(1L)),
    /**
     * 存放物联网设备影子
     */
    IOT_DEVICE_SHADOW_CACHE("iot:device:shadow:", Duration.ofHours(1)),
    /**
     * hash表结构 存放物联网产品信息
     */
    IOT_PRODUCT_INFO_CACHE("iot:productInfo", Duration.ofDays(3)),
    ;

    private final String key;

    private final Duration duration;
}
