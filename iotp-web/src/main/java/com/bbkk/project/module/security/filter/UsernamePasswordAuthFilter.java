package com.bbkk.project.module.security.filter;

import com.alibaba.fastjson2.JSONObject;
import com.bbkk.project.module.security.data.LoginParams;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * spring security 登陆过滤器
 * 用来获取登陆的账号和密码
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-29 18:11
 **/
public class UsernamePasswordAuthFilter extends AbstractAuthenticationProcessingFilter {

    private final static String LOGIN_URI = "/api/auth/login";

    public UsernamePasswordAuthFilter() {
        super(new AntPathRequestMatcher(LOGIN_URI, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        // 从请求数据中读取 username 和 password
        LoginParams loginParams = httpServletRequest2LoginParams(request);

        String username = loginParams.getUsername() == null ? "" : loginParams.getUsername();
        String password = loginParams.getPassword() == null ? "" : loginParams.getPassword();
        // todo 重构为使用密文登陆，在此对密文进行解密
        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username, password);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * 从 HttpServletRequest 中读取请求数据 并转换为 LoginParams 对象
     *
     * @param request HttpServletRequest
     * @return LoginParams
     */
    private LoginParams httpServletRequest2LoginParams(HttpServletRequest request) {
        String body = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder responseStringBuilder = new StringBuilder();
            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                responseStringBuilder.append(inputString);
            }
            body = responseStringBuilder.toString();
            return JSONObject.parseObject(body, LoginParams.class);
        } catch (IOException e) {
            throw new BadCredentialsException("认证凭据无效");
        }
    }
}
