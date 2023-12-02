package com.bbkk.project.module.upms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 用户与角色关联表 实体
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023/11/29 18:56
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "system_user_role")
public class SystemUserRole {

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private Long roleId;
}