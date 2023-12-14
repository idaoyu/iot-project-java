package com.bbkk.project.module.product.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 产品认证类型
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-14 19:54
 **/
@Getter
@AllArgsConstructor
public enum ProductAuthType {

    /**
     * 一个产品一个密钥
     */
    BIND_PRODUCT("product"),
    /**
     * 一个设备一个密钥
     */
    BIND_DEVICE("device"),
    ;


    private final String type;

}
