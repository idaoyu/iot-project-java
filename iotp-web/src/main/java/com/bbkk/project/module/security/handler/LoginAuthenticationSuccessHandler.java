package com.bbkk.project.module.security.handler;

import com.bbkk.project.module.upms.entity.SystemUser;
import com.bbkk.project.module.web.data.ResultBody;
import com.bbkk.project.utils.HttpServletUtil;
import com.bbkk.project.utils.JwtUtil;
import com.google.common.collect.Maps;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登陆成功处理器
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-10-28 18:16
 **/
@Component
@RequiredArgsConstructor
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        SystemUser user = (SystemUser) authentication.getPrincipal();
        // 生成 jwt token
        Map<String, Object> payload = Maps.newHashMap();
        payload.put("username", user.getUsername());
        // jwt 过期时间 2 天
        String jwt = JwtUtil.createJwt(payload, 60 * 60 * 24 * 2);

        // 包装统一返回结构
        HashMap<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("token", jwt);
        ResultBody resultBody = ResultBody.ok(resultMap);
        HttpServletUtil.writeHttpServletResponse(response, resultBody);
    }
}
