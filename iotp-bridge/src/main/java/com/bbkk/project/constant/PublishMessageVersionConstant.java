package com.bbkk.project.constant;

import com.bbkk.project.data.v1.V1PublishMessageDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 发布消息数据格式 版本 枚举
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-21 19:21
 **/
@Getter
@AllArgsConstructor
public enum PublishMessageVersionConstant {

    V1("1.0", V1PublishMessageDTO.class),
    ;

    private final String version;

    private final Class<?> clazz;

}
