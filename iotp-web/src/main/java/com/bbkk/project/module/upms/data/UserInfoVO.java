package com.bbkk.project.module.upms.data;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 用户信息 vo
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-03 12:27
 **/
@Data
@Builder
public class UserInfoVO {

    /**
     * 账号
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String profilePhoto;

    /**
     * 角色列表
     */
    private List<String> role;

    /**
     * 权限列表
     */
    private List<String> permission;

}
