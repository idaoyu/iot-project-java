package com.bbkk.project.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * spring security 认证、鉴权 产生异常时的状态码枚举
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-29 18:23
 **/
@Getter
@AllArgsConstructor
public enum SecurityErrorCodeConstant {

    /**
     * 用户名或密码错误时
     */
    USERNAME_PASSWORD_NOT_FOUNT("10000", "用户名或密码错误"),
    /**
     * 用户未登陆但访问需要登陆的接口时
     */
    NEED_LOGIN("10001", "需要登陆"),
    /**
     * 用户已登陆，但访问未授权的接口时
     */
    DOES_NOT_HAVE_REQUIRED_PERMISSIONS_FOR_ACCESS("10002", "无权限访问"),
    /**
     * 未知原因抛出的认证/鉴权异常
     */
    UNKNOWN_AUTHENTICATION_ERROR("401", "认证鉴权时发生错误"),
    // todo 完善更多情况下的状态码
    ;

    private final String code;

    private final String message;

}
