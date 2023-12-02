package com.bbkk.project.module.upms.data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 修改权限接口入参
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-02 11:29
 **/
@Data
public class UpdatePermissionParams {

    /**
     * 权限名字
     */
    @NotEmpty(message = "权限名字不能为空")
    private String name;

    /**
     * 权限值
     */
    @NotEmpty(message = "权限值不能为空")
    private String value;

    /**
     * 权限所指的 uri
     */
    @NotEmpty(message = "uri 不能为空")
    private String uri;

    /**
     * 权重 通配符应低于全路径
     */
    @NotNull(message = "权重不能为空")
    private Integer weight;

}
