package com.bbkk.project.module.upms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bbkk.project.module.upms.data.CreatePermissionParams;
import com.bbkk.project.module.upms.data.PageGetPermissionParams;
import com.bbkk.project.module.upms.data.UpdatePermissionParams;
import com.bbkk.project.module.upms.entity.SystemPermission;
import com.bbkk.project.module.upms.service.PermissionManageService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 权限管理接口
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-02 11:02
 **/
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manage/permission")
public class PermissionManageController {

    private final PermissionManageService permissionManageService;

    /**
     * 分页查询权限
     *
     * @param params PageGetPermissionParams
     * @return IPage<SystemPermission>
     */
    @GetMapping
    public IPage<SystemPermission> pageGetPermission(@Validated PageGetPermissionParams params) {
        return permissionManageService.pageGetPermission(params);
    }

    /**
     * 创建权限
     *
     * @param params CreatePermissionParams
     * @return 成功时返回 "成功"
     */
    @PostMapping
    public String createPermission(@RequestBody @Validated CreatePermissionParams params) {
        return permissionManageService.createPermission(params);
    }

    /**
     * 修改权限接口
     *
     * @param params UpdatePermissionParams
     * @param id     要修改的权限id
     * @return 成功时返回 "成功"
     */
    @PutMapping
    public String updatePermission(
            @RequestBody @Validated UpdatePermissionParams params,
            @NotNull(message = "要修改的权限id不能为空") Long id
    ) {
        return permissionManageService.updatePermission(params, id);
    }

    /**
     * 删除权限接口
     *
     * @param id 要删除的权限id
     * @return 成功时返回 "成功"
     */
    @DeleteMapping
    public String deletePermission(@NotNull(message = "要删除的权限id不能为空") Long id) {
        return permissionManageService.deletePermission(id);
    }

}
