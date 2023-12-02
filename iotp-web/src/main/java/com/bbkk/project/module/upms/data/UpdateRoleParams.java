package com.bbkk.project.module.upms.data;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 修改角色接口入参
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-02 10:42
 **/
@Data
public class UpdateRoleParams {

    @NotEmpty(message = "角色名字不能为空")
    private String name;

    @NotEmpty(message = "角色值不能为空")
    private String value;

    @NotEmpty(message = "角色描述不能为空")
    private String description;

}
