package com.bbkk.project.module.upms.data;

import lombok.Data;

import java.util.List;

/**
 * 角色及其对应的权限
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-29 19:59
 **/
@Data
public class RolePermissionDTO {

    /**
     * 角色
     */
    private String role;

    /**
     * 权限集合
     */
    private List<PermissionDTO> permissionList;

}
