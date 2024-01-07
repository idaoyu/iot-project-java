package com.bbkk.project.module.security.handler;

import com.bbkk.project.data.ResultBody;
import com.bbkk.project.utils.HttpServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.bbkk.project.constant.SecurityErrorCodeConstant.UNKNOWN_AUTHENTICATION_ERROR;
import static com.bbkk.project.constant.SecurityErrorCodeConstant.USERNAME_PASSWORD_NOT_FOUNT;


/**
 * 登陆失败处理器
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-10-28 19:22
 **/
@Slf4j
@Component
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        ResultBody resp;
        if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
            // 用户名或密码错误 || 用户使用错误的凭据 登陆时
            resp = ResultBody.error(USERNAME_PASSWORD_NOT_FOUNT.getCode(), USERNAME_PASSWORD_NOT_FOUNT.getMessage());
        } else {
            // 其他未指定的认证异常
            resp = ResultBody.error(UNKNOWN_AUTHENTICATION_ERROR.getCode(), UNKNOWN_AUTHENTICATION_ERROR.getMessage());
            log.warn("认证时产生未定义的异常", exception);
        }
        // todo 后续补全其他鉴权异常
        HttpServletUtil.writeHttpServletResponse(response, resp);
    }
}
