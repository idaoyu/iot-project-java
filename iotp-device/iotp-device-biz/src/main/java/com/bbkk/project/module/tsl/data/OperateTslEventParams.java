package com.bbkk.project.module.tsl.data;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 * 创建/修改 物模型事件接口入参
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-11 18:49
 **/
@Data
public class OperateTslEventParams {

    /**
     * 事件id
     */
    @NotEmpty(message = "事件id不能为空")
    private String id;

    /**
     * 事件名字
     */
    @NotEmpty(message = "事件名字不能为空")
    private String name;

    /**
     * 事件描述
     */
    @NotEmpty(message = "事件描述不能为空")
    private String description;

    /**
     * 事件类型
     */
    @NotEmpty(message = "事件类型不能为空")
    private String type;

    /**
     * 事件绑定的属性id集合
     */
    @NotEmpty(message = "事件属性不能为空")
    private List<String> propertyIdList;

}
