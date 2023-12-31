package com.bbkk.project.module.upms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

/**
 * 角色表 实体
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023/11/29 18:55
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "system_role")
public class SystemRole {

    /**
     * 角色id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 角色名字
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 角色在系统中的标识
     */
    @TableField(value = "`value`")
    private String value;

    /**
     * 角色描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
}