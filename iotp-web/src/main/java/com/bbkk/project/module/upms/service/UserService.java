package com.bbkk.project.module.upms.service;

import com.bbkk.project.module.upms.data.UserInfoVO;
import com.bbkk.project.module.upms.entity.SystemUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户相关操作接口 业务逻辑
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-03 12:30
 **/
@Service
@RequiredArgsConstructor
public class UserService {

    public UserInfoVO getInfo(SystemUser user) {
        UserInfoVO.UserInfoVOBuilder builder = UserInfoVO.builder();
        builder.username(user.getUsername());
        builder.nickname(user.getNickname());
        builder.profilePhoto(user.getProfilePhoto());
        List<String> authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        builder.role(
                authorities.stream().filter(v -> v.startsWith("ROLE_")).toList()
        );
        builder.permission(
                authorities.stream().filter(v -> !v.startsWith("ROLE_")).toList()
        );
        return builder.build();
    }
}
