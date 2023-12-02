package com.bbkk.project.module.upms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 角色与权限关联表 实体
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023/11/29 18:55
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "system_role_permission")
public class SystemRolePermission {

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 权限id
     */
    @TableField(value = "permission_id")
    private Long permissionId;
}