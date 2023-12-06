package com.bbkk.project.module.tsl.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举数据类型值的来源
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-06 20:45
 **/
@Getter
@AllArgsConstructor
public enum EnumValueSource {

    /**
     * 属性
     */
    PROPERTY("property"),
    /**
     * 方法参数
     */
    METHOD_PARAMS("methodParams");

    private final String source;

}
