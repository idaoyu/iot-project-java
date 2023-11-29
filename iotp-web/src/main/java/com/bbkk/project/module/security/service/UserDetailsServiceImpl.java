package com.bbkk.project.module.security.service;

import com.bbkk.project.constant.SecurityErrorCodeConstant;
import com.bbkk.project.module.upms.data.RolePermissionDTO;
import com.bbkk.project.module.upms.entity.SystemUser;
import com.bbkk.project.module.upms.mapper.SystemRoleMapper;
import com.bbkk.project.module.upms.service.ISystemUserService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bbkk.project.constant.RedisCacheKeyConstant.ALL_ROLE_PERMISSION_LIST;

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
    private final SystemRoleMapper systemRoleMapper;
    private final RedissonClient redissonClient;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 加载用户信息
        SystemUser user = systemUserService.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(SecurityErrorCodeConstant.USERNAME_PASSWORD_NOT_FOUNT.getMessage());
        }
        // 查询用户拥有的角色
        List<String> roleList = systemRoleMapper.findRoleListByUserId(user.getId());
        // 获取系统全部的角色权限对应数据
        List<RolePermissionDTO> rolePermissionList = tryGetRolePermissionFromCache();
        // 过滤出用户拥有的角色以及权限
        List<RolePermissionDTO> ownRolePermissionList = rolePermissionList
                .stream().filter(v -> roleList.contains(v.getRole())).toList();
        List<SimpleGrantedAuthority> authorities = Lists.newArrayList();
        for (RolePermissionDTO rolePermissionDTO : ownRolePermissionList) {
            String role = rolePermissionDTO.getRole();
            // 如果数据库中配置的角色是 ROLE_ 开头的则直接塞进去 否则添加上
            // 为了方便区分权限和角色 spring security 对这玩应好像也有说法
            if (role.startsWith("ROLE_")) {
                authorities.add(new SimpleGrantedAuthority(role));
            } else {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            }
            // 把权限转换成 SimpleGrantedAuthority 对象 塞进 authorities 中
            rolePermissionDTO.getPermissionList().stream()
                    .map(v -> new SimpleGrantedAuthority(v.getValue()))
                    .forEach(authorities::add);
        }
        // 最后对用户的角色权限集合去重 并设置到用户对象中
        user.setAuthorities(authorities.stream().distinct().toList());
        return user;
    }

    public List<RolePermissionDTO> tryGetRolePermissionFromCache() {
        RBucket<List<RolePermissionDTO>> bucket = redissonClient.getBucket(ALL_ROLE_PERMISSION_LIST.getKey());
        // 先尝试从缓存中获取数据
        List<RolePermissionDTO> list = bucket.get();
        if (list != null) {
            return list;
        }
        // 查询数据库
        list = systemRoleMapper.findAllRolePermission();
        // 设置缓存
        bucket.set(list, ALL_ROLE_PERMISSION_LIST.getDuration());
        return list;
    }
}
