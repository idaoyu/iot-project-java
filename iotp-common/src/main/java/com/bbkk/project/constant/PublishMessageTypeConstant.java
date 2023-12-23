package com.bbkk.project.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 发布消息数据类型枚举类
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-21 19:32
 **/
@Getter
@AllArgsConstructor
public enum PublishMessageTypeConstant {

    /**
     * 属性上报
     */
    PROPERTY_REPORT("property"),

    /**
     * 触发事件
     */
    TRIGGER_EVENT("event"),
    ;

    /**
     * 类型
     */
    private final String type;

}
