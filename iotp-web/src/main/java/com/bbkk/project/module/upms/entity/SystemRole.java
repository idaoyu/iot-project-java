package com.bbkk.project.module.upms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

/**
 * 角色表 实体
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023/11/29 18:55
 **/
@Schema(description = "角色表")
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
    @Schema(description = "角色id")
    private Long id;

    /**
     * 角色名字
     */
    @TableField(value = "`name`")
    @Schema(description = "角色名字")
    private String name;

    /**
     * 角色在系统中的标识
     */
    @TableField(value = "`value`")
    @Schema(description = "角色在系统中的标识")
    private String value;

    /**
     * 角色描述
     */
    @TableField(value = "description")
    @Schema(description = "角色描述")
    private String description;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @Schema(description = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    @Schema(description = "修改时间")
    private Date updateTime;
}