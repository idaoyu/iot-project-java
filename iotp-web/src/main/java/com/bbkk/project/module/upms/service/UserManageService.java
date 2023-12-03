package com.bbkk.project.module.upms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bbkk.project.constant.SystemDefaultValueConstant;
import com.bbkk.project.exception.BizException;
import com.bbkk.project.module.upms.data.CreateUserParams;
import com.bbkk.project.module.upms.data.PageGetUserParams;
import com.bbkk.project.module.upms.data.UpdateUserParams;
import com.bbkk.project.module.upms.entity.SystemUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 用户管理接口业务逻辑
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-03 11:51
 **/
@Service
@RequiredArgsConstructor
public class UserManageService {

    private final ISystemUserService systemUserService;
    private final ISystemUserRoleService systemUserRoleService;
    private final PasswordEncoder passwordEncoder;

    public IPage<SystemUser> pageGetUser(PageGetUserParams params) {
        return systemUserService.pageGetUser(params);
    }

    public String createUser(CreateUserParams params) {
        SystemUser user = systemUserService.findUserByUsername(params.getUsername());
        if (user != null) {
            throw new BizException("用户名已存在");
        }
        SystemUser.SystemUserBuilder builder = SystemUser.builder();
        builder.username(params.getUsername());
        builder.password(passwordEncoder.encode(params.getPassword()));
        builder.nickname(params.getNickname());
        builder.enable(true);
        builder.profilePhoto(SystemDefaultValueConstant.PROFILE_PHOTO_URL.getValue());
        builder.createTime(new Date());
        builder.updateTime(new Date());
        boolean success = systemUserService.save(builder.build());
        if (!success) {
            throw new BizException("创建用户失败，请稍后重试");
        }
        return "成功";
    }

    public String updateUser(UpdateUserParams params, Long id) {
        SystemUser user = systemUserService.getOptById(id).orElseThrow(() -> new BizException("要修改的用户不存在"));
        user.setPassword(passwordEncoder.encode(params.getPassword()));
        user.setNickname(params.getNickname());
        user.setEnable(params.getEnable());
        user.setProfilePhoto(params.getProfilePhoto());
        user.setUpdateTime(new Date());
        boolean success = systemUserService.updateById(user);
        if (!success) {
            throw new BizException("修改用户失败，请稍后重试");
        }
        return "成功";
    }

    @Transactional(rollbackFor = Exception.class)
    public String removeUser(Long id) {
        systemUserService.getOptById(id).orElseThrow(() -> new BizException("要删除的用户不存在"));
        boolean rmUser = systemUserService.removeById(id);
        if (!rmUser) {
            throw new BizException("删除用户失败，请稍后重试");
        }
        Boolean rmUserRole = systemUserRoleService.removeByUserId(id);
        if (!rmUserRole) {
            throw new BizException("删除用户失败，请稍后重试");
        }
        return "成功";
    }
}
