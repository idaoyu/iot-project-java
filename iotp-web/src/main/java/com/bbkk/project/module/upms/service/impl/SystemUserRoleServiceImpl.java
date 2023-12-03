package com.bbkk.project.module.upms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbkk.project.module.upms.entity.SystemUserRole;
import com.bbkk.project.module.upms.mapper.SystemUserRoleMapper;
import com.bbkk.project.module.upms.service.ISystemUserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-29 19:04
 **/
@Service
public class SystemUserRoleServiceImpl extends ServiceImpl<SystemUserRoleMapper, SystemUserRole> implements ISystemUserRoleService {

    @Override
    public Boolean removeByRoleId(Long roleId) {
        LambdaQueryWrapper<SystemUserRole> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SystemUserRole::getRoleId, roleId);
        boolean exists = super.exists(wrapper);
        if (exists) {
            return super.remove(wrapper);
        }
        return true;
    }

    @Override
    public Boolean removeByUserId(Long id) {
        LambdaQueryWrapper<SystemUserRole> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SystemUserRole::getUserId, id);
        boolean exists = super.exists(wrapper);
        if (exists) {
            return super.remove(wrapper);
        }
        return true;
    }
}
