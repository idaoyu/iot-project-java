package com.bbkk.project.module.security.data;

import lombok.Data;

/**
 * 登陆请求入参
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-29 18:14
 **/
@Data
public class LoginParams {

    private String username;

    private String password;

}
