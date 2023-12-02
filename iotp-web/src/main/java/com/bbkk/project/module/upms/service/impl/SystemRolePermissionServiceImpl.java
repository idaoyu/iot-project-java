package com.bbkk.project.module.upms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbkk.project.module.upms.entity.SystemRolePermission;
import com.bbkk.project.module.upms.mapper.SystemRolePermissionMapper;
import com.bbkk.project.module.upms.service.ISystemRolePermissionService;
import org.springframework.stereotype.Service;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-29 19:01
 **/
@Service
public class SystemRolePermissionServiceImpl extends ServiceImpl<SystemRolePermissionMapper, SystemRolePermission>
        implements ISystemRolePermissionService {

    @Override
    public Boolean removeByRoleId(Long roleId) {
        LambdaQueryWrapper<SystemRolePermission> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SystemRolePermission::getRoleId, roleId);
        // 先查有没有绑定的记录
        boolean exists = super.exists(wrapper);
        if (exists) {
            // 如果存在记录 则删除
            return super.remove(wrapper);
        }
        // 否则直接返回删除成功 因为没有
        return true;
    }
}
