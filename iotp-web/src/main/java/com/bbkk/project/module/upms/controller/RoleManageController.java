package com.bbkk.project.module.upms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bbkk.project.module.upms.data.CreateRoleParams;
import com.bbkk.project.module.upms.data.PageGetRoleParams;
import com.bbkk.project.module.upms.data.UpdateRoleParams;
import com.bbkk.project.module.upms.entity.SystemRole;
import com.bbkk.project.module.upms.service.RoleManageService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 角色管理接口
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-02 09:52
 **/
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manage/role")
public class RoleManageController {

    private final RoleManageService roleManageService;

    @GetMapping
    public IPage<SystemRole> pageGetRole(@Validated PageGetRoleParams params) {
        return roleManageService.pageGetRole(params);
    }

    @PostMapping
    public String createRole(@RequestBody @Validated CreateRoleParams params) {
        return roleManageService.createRole(params);
    }

    @PutMapping
    public String updateRole(
            @RequestBody @Validated UpdateRoleParams params,
            @RequestParam @NotNull(message = "要修改的角色id不能为空") Long id
    ) {
        return roleManageService.updateRole(params, id);
    }

    @DeleteMapping
    public String deleteRole(@NotNull(message = "要删除的角色id不能为空") Long id) {
        return roleManageService.deleteRole(id);
    }

}
