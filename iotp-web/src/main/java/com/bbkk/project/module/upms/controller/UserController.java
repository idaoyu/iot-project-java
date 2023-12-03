package com.bbkk.project.module.upms.controller;

import com.bbkk.project.module.upms.data.UserInfoVO;
import com.bbkk.project.module.upms.entity.SystemUser;
import com.bbkk.project.module.upms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户操作相关接口
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-03 12:25
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    /**
     * 获取当前登陆的用户信息
     */
    @GetMapping("/info")
    public UserInfoVO getInfo(@AuthenticationPrincipal SystemUser user) {
        return userService.getInfo(user);
    }

}
