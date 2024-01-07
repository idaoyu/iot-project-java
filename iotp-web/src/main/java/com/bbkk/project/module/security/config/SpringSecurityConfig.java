package com.bbkk.project.module.security.config;

import com.bbkk.project.data.ResultBody;
import com.bbkk.project.module.security.filter.TokenVerifyFilter;
import com.bbkk.project.module.security.filter.UsernamePasswordAuthFilter;
import com.bbkk.project.module.security.handler.LoginAuthenticationFailureHandler;
import com.bbkk.project.module.security.handler.LoginAuthenticationSuccessHandler;
import com.bbkk.project.module.security.service.UserDetailsServiceImpl;
import com.bbkk.project.utils.HttpServletUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.bbkk.project.constant.SecurityErrorCodeConstant.DOES_NOT_HAVE_REQUIRED_PERMISSIONS_FOR_ACCESS;
import static com.bbkk.project.constant.SecurityErrorCodeConstant.NEED_LOGIN;

/**
 * spring security 配置类
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-29 19:14
 **/
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final LoginAuthenticationSuccessHandler successHandler;
    private final LoginAuthenticationFailureHandler failureHandler;
    private final TokenVerifyFilter tokenVerifyFilter;

    /**
     * 注册 BCryptPasswordEncoder 用于对密码进行加密
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 注册 AuthenticationManager Bean
     * 该 Bean 在 UsernamePasswordAuthFilter 过滤器组装完认证对象后，会将认证对象传入该方法进行认证
     *
     * @return AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return new ProviderManager(provider);
    }

    /**
     * spring security 配置的主体
     *
     * @return SecurityFilterChain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        UsernamePasswordAuthFilter filter = new UsernamePasswordAuthFilter();
        // 为 UsernamePasswordAuthFilter 过滤器设置 AuthenticationManager
        filter.setAuthenticationManager(authenticationManager());
        // 设置认证成功、失败处理器
        filter.setAuthenticationSuccessHandler(successHandler);
        filter.setAuthenticationFailureHandler(failureHandler);
        httpSecurity
                // 取消默认的登陆页面
                .formLogin(AbstractHttpConfigurer::disable)
                // 取消默认的登出页面
                .logout(AbstractHttpConfigurer::disable)
                // 关闭 csrf 前后端分离架构不需要这玩应
                .csrf(AbstractHttpConfigurer::disable)
                // 禁用 session 使用自己的 token 拦截器处理用户状态
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 禁用 httpBasic 使用 POST 传输数据
                .httpBasic(AbstractHttpConfigurer::disable)
                // 将 token 校验过滤器 添加到 UsernamePasswordAuthenticationFilter 过滤器前
                .addFilterBefore(tokenVerifyFilter, UsernamePasswordAuthenticationFilter.class)
                // 将自定义的登陆过滤器 添加到 UsernamePasswordAuthenticationFilter 过滤器前
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                // 登出逻辑
                .logout(customizer -> {
                    customizer
                            // 登出接口 uri
                            .logoutUrl("/api/auth/logout")
                            // 登出的处理逻辑
                            .addLogoutHandler((req, resp, authentication) -> {
                                // todo 把自定义用户态的缓存清掉
                            })
                            // 登出成功后的处理逻辑
                            .logoutSuccessHandler((req, resp, authentication) -> {
                                HttpServletUtil.writeHttpServletResponse(resp, ResultBody.ok(null));
                            });
                })
                // 异常处理器
                .exceptionHandling(v -> {
                    // 当用户未通过认证时访问受保护的资源时
                    v.authenticationEntryPoint((req, resp, authException) -> {
                        ResultBody resultBody = ResultBody.error(NEED_LOGIN.getCode(), NEED_LOGIN.getMessage());
                        HttpServletUtil.writeHttpServletResponse(resp, resultBody);
                    });
                    // 当用户通过认证时访问受保护的资源，但是权限不足时
                    v.accessDeniedHandler((req, resp, accessDeniedException) -> {
                        ResultBody resultBody =
                                ResultBody.error(
                                        DOES_NOT_HAVE_REQUIRED_PERMISSIONS_FOR_ACCESS.getCode(), DOES_NOT_HAVE_REQUIRED_PERMISSIONS_FOR_ACCESS.getMessage());
                        HttpServletUtil.writeHttpServletResponse(resp, resultBody);
                    });
                });
        // 鉴权逻辑
        httpSecurity.authorizeHttpRequests(register -> {
            // 放行 登陆 登出接口
//            register.requestMatchers(
//                    // 放行 Knife4j 接口文档
//                    "/favicon.ico", "/doc.html", "/webjars/**", "/v3/api-docs/**",
//                    // 放行登陆 退出 接口
//                    "/api/auth/login", "/api/auth/logout",
//                    "/api/cert"
//            ).permitAll();
            register.anyRequest().permitAll();
        });
        return httpSecurity.build();
    }
}
