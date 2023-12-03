package com.bbkk.project.module.upms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bbkk.project.module.upms.data.CreateUserParams;
import com.bbkk.project.module.upms.data.PageGetUserParams;
import com.bbkk.project.module.upms.data.UpdateUserParams;
import com.bbkk.project.module.upms.entity.SystemUser;
import com.bbkk.project.module.upms.service.UserManageService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理接口
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-03 11:42
 **/
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manage/user")
public class UserManageController {

    private final UserManageService userManageService;

    @GetMapping
    public IPage<SystemUser> pageGetUser(@Validated PageGetUserParams params) {
        return userManageService.pageGetUser(params);
    }

    @PostMapping
    public String createUser(@RequestBody @Validated CreateUserParams params) {
        return userManageService.createUser(params);
    }

    @PutMapping
    public String updateUser(
            @RequestBody @Validated UpdateUserParams params,
            @RequestParam @NotNull(message = "要修改的用户id不能为空") Long id
    ) {
        return userManageService.updateUser(params, id);
    }

    @DeleteMapping
    public String removeUser(@NotNull(message = "要删除的用户id不能为空") Long id) {
        return userManageService.removeUser(id);
    }

}
