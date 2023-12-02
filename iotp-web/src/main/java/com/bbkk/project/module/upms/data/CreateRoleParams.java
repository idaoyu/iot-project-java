package com.bbkk.project.module.upms.data;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 创建角色
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-02 10:32
 **/
@Data
public class CreateRoleParams {

    /**
     * 角色名字
     */
    @NotEmpty(message = "角色名字不能为空")
    private String name;

    /**
     * 角色在系统中的标识
     */
    @NotEmpty(message = "角色值不能为空")
    private String value;

    /**
     * 角色描述
     */
    @NotEmpty(message = "角色描述不能为空")
    private String description;

}
