package com.bbkk.project.module.product.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 物模型类别枚举
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-12 20:28
 **/
@Getter
@AllArgsConstructor
public enum TslTypeConstant {

    /**
     * 属性
     */
    PROPERTY("property"),
    /**
     * 方法
     */
    METHOD("method"),
    /**
     * 事件
     */
    EVENT("event"),
    ;

    private final String value;

}
