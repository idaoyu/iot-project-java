package com.bbkk.project.module.security.service;

import com.bbkk.project.constant.SecurityErrorCodeConstant;
import com.bbkk.project.module.upms.entity.SystemUser;
import com.bbkk.project.module.upms.service.ISystemUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 从数据库中加载用户信息
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-29 19:09
 **/
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ISystemUserService systemUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 加载用户信息
        SystemUser user = systemUserService.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(SecurityErrorCodeConstant.USERNAME_PASSWORD_NOT_FOUNT.getMessage());
        }
        // todo 查询用户所拥有的角色和权限
        return user;
    }
}
