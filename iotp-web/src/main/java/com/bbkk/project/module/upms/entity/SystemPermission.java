package com.bbkk.project.module.upms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

/**
 * 权限表 实体
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023/11/29 18:55
 **/
@Schema(description = "权限表")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "system_permission")
public class SystemPermission {

    /**
     * 权限id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @Schema(description = "权限id")
    private Long id;

    /**
     * 权限名字
     */
    @TableField(value = "`name`")
    @Schema(description = "权限名字")
    private String name;

    /**
     * 权限在系统中的标识
     */
    @TableField(value = "`value`")
    @Schema(description = "权限在系统中的标识")
    private String value;

    /**
     * 所指定的接口路径
     */
    @TableField(value = "uri")
    @Schema(description = "所指定的接口路径")
    private String uri;

    /**
     * 权重，通配符uri权重应低于完整uri
     */
    @TableField(value = "weight")
    @Schema(description = "权重，通配符uri权重应低于完整uri")
    private Integer weight;

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