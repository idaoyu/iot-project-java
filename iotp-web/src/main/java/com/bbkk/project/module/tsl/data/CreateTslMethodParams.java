package com.bbkk.project.module.tsl.data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 创建物模型方法接口入参
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-05 21:26
 **/
@Data
public class CreateTslMethodParams {

    /**
     * 方法唯一标识
     */
    @NotEmpty(message = "方法唯一标识不能为空")
    private String id;

    /**
     * 方法名字
     */
    @NotEmpty(message = "方法名字")
    private String name;

    /**
     * 描述
     */
    @NotEmpty(message = "描述")
    private String description;

    /**
     * 是异步调用
     */
    @NotNull(message = "是否是异步调用不能为空")
    private Boolean asynchronous;

    /**
     * 方法输入参数
     */
    @Valid
    @NotEmpty(message = "方法输入参数不能为空")
    private List<OperateTslMethodParamsDTO> inputParams;

    /**
     * 方法输出参数
     */
    @Valid
    @NotEmpty(message = "方法输出参数不能为空")
    private List<OperateTslMethodParamsDTO> outputParams;


}
