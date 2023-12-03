package com.bbkk.project.module.upms.data;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 创建用户接口入参
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-03 11:55
 **/
@Data
public class CreateUserParams {

    /**
     * 用户账号
     */
    @NotEmpty(message = "账号不能为空")
    private String username;

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

}
