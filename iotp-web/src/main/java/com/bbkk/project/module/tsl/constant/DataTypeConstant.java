package com.bbkk.project.module.tsl.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 物模型 数据类型枚举
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-03 17:25
 **/
@Getter
@AllArgsConstructor
public enum DataTypeConstant {

    /**
     * 整数类型
     */
    INT("int"),
    /**
     * 浮点数类型
     */
    FLOAT("float"),
    /**
     * 枚举类型
     */
    ENUM("enum"),
    ;

    private final String dataType;

}
