package com.bbkk.project.module.security.handler;

import cn.hutool.core.util.IdUtil;
import com.bbkk.project.module.upms.entity.SystemUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 登陆成功处理器
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-10-28 18:16
 **/
@Component
@RequiredArgsConstructor
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final RedissonClient redissonClient;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        SystemUser user = (SystemUser) authentication.getPrincipal();
        // 生成 token
        String token = IdUtil.randomUUID();
        // todo 把 token 和用户对象扔缓存里
    }
}
