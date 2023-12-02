package com.bbkk.project.module.upms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bbkk.project.exception.BizException;
import com.bbkk.project.module.upms.data.CreatePermissionParams;
import com.bbkk.project.module.upms.data.PageGetPermissionParams;
import com.bbkk.project.module.upms.data.UpdatePermissionParams;
import com.bbkk.project.module.upms.entity.SystemPermission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 权限接口业务逻辑
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-02 11:22
 **/
@Service
@RequiredArgsConstructor
public class PermissionManageService {

    private final ISystemPermissionService systemPermissionService;
    private final ISystemRolePermissionService systemRolePermissionService;

    public IPage<SystemPermission> pageGetPermission(PageGetPermissionParams params) {
        return systemPermissionService.pageGetPermission(params);
    }

    public String createPermission(CreatePermissionParams params) {
        SystemPermission.SystemPermissionBuilder builder = SystemPermission.builder();
        builder.name(params.getName());
        builder.value(params.getValue());
        builder.uri(params.getUri());
        builder.weight(params.getWeight());
        boolean success = systemPermissionService.save(builder.build());
        if (!success) {
            throw new BizException("创建权限失败，请稍后重试");
        }
        return "成功";
    }

    public String updatePermission(UpdatePermissionParams params, Long id) {
        SystemPermission permission = systemPermissionService.getOptById(id)
                .orElseThrow(() -> new BizException("要修改的权限不存在"));
        permission.setName(params.getName());
        permission.setValue(params.getValue());
        permission.setUri(params.getUri());
        permission.setWeight(params.getWeight());
        boolean success = systemPermissionService.updateById(permission);
        if (!success) {
            throw new BizException("修改权限失败，请稍后重试");
        }
        return "成功";
    }

    @Transactional(rollbackFor = Exception.class)
    public String deletePermission(Long id) {
        systemPermissionService.getOptById(id).orElseThrow(() -> new BizException("要删除权限不存在"));
        boolean rmPermission = systemPermissionService.removeById(id);
        if (!rmPermission) {
            throw new BizException("删除权限失败，请稍后重试");
        }
        Boolean rmPermissionRole = systemRolePermissionService.removeByPermissionId(id);
        if (!rmPermissionRole) {
            throw new BizException("删除权限失败，请稍后重试");
        }
        return "成功";
    }
}
