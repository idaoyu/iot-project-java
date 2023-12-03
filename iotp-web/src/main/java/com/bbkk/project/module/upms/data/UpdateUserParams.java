package com.bbkk.project.module.upms.data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 修改用户信息接口入参
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-03 12:03
 **/
@Data
public class UpdateUserParams {

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    private String password;

    /**
     * 昵称
     */
    @NotEmpty(message = "昵称不能为空")
    private String nickname;

    /**
     * 是否启用
     */
    @NotNull(message = "账号状态不能为空")
    private Boolean enable;

    /**
     * 头像
     */
    @NotEmpty(message = "头像不能为空")
    private String profilePhoto;

}
