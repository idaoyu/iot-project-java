package com.bbkk.project.module.upms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bbkk.project.exception.BizException;
import com.bbkk.project.module.upms.data.CreateRoleParams;
import com.bbkk.project.module.upms.data.PageGetRoleParams;
import com.bbkk.project.module.upms.data.UpdateRoleParams;
import com.bbkk.project.module.upms.entity.SystemRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 角色管理接口 业务逻辑类
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-02 10:08
 **/
@Service
@RequiredArgsConstructor
public class RoleManageService {

    private final ISystemRoleService systemRoleService;
    private final ISystemRolePermissionService systemRolePermissionService;
    private final ISystemUserRoleService systemUserRoleService;

    public IPage<SystemRole> pageGetRole(PageGetRoleParams params) {
        return systemRoleService.pageGetRole(params);
    }

    public String createRole(CreateRoleParams params) {
        SystemRole.SystemRoleBuilder builder = SystemRole.builder();
        builder.name(params.getName());
        builder.value(params.getValue());
        builder.description(params.getDescription());
        builder.createTime(new Date());
        builder.updateTime(new Date());
        boolean success = systemRoleService.save(builder.build());
        if (!success) {
            throw new BizException("创建角色失败，请稍后重试");
        }
        return "成功";
    }

    public String updateRole(UpdateRoleParams params, Long id) {
        SystemRole role = systemRoleService.getOptById(id).orElseThrow(() -> new BizException("要修改的角色不存在"));
        role.setName(params.getName());
        role.setValue(params.getValue());
        role.setDescription(params.getDescription());
        boolean success = systemRoleService.updateById(role);
        if (!success) {
            throw new BizException("修改角色失败，请稍后重试");
        }
        return "成功";
    }

    @Transactional(rollbackFor = Exception.class)
    public String deleteRole(Long id) {
        // 删除角色表
        systemRoleService.getOptById(id).orElseThrow(() -> new BizException("要删除的角色不存在"));
        boolean rmRole = systemRoleService.removeById(id);
        if (!rmRole) {
            throw new BizException("删除角色失败，请稍后重试");
        }
        Boolean rmRolePermission = systemRolePermissionService.removeByRoleId(id);
        if (!rmRolePermission) {
            throw new BizException("删除角色失败，请稍后重试");
        }

        Boolean rmUserRole = systemUserRoleService.removeByRoleId(id);
        if (!rmUserRole) {
            throw new BizException("删除角色失败，请稍后重试");
        }

        return "成功";
    }
}
