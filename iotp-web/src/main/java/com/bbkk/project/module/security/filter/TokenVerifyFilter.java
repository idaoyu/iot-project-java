package com.bbkk.project.module.security.filter;


import com.bbkk.project.module.security.service.UserDetailsServiceImpl;
import com.bbkk.project.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


/**
 * token 校验过滤器
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-10-29 13:47
 **/
@Component
@RequiredArgsConstructor
public class TokenVerifyFilter extends OncePerRequestFilter {

    private final UserDetailsServiceImpl userDetailsService;

    private static final String TOKEN_KEY = "Authentication";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response
            , FilterChain chain) throws ServletException, IOException {
        String jwtToken = request.getHeader(TOKEN_KEY);
        if (StringUtils.isNotBlank(jwtToken)) {
            // 如果 当前请求携带了 token 则组装 SecurityContextHolder
            assemblyCertificationContext(jwtToken, request);
        }
        chain.doFilter(request, response);
    }

    private void assemblyCertificationContext(String jwtToken, HttpServletRequest request) {
        String username;
        // 校验 jwt token
        try {
            username = JwtUtil.parseJwtToken(jwtToken).get("username").toString();
        } catch (Exception ex) {
            // todo jwt 可能过期 也可能无效 后续补全具体区分的错误处理逻辑
            return;
        }
        if (StringUtils.isBlank(username)) {
            return;
        }
        // 加载用户信息
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // 创建认证对象
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails
                , null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // 更新 spring security 上下文对象
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
