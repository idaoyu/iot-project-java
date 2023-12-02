package com.bbkk.project.module.upms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

/**
 * 权限表 实体
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023/11/29 18:55
 **/
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
    private Long id;

    /**
     * 权限名字
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 权限在系统中的标识
     */
    @TableField(value = "`value`")
    private String value;

    /**
     * 所指定的接口路径
     */
    @TableField(value = "uri")
    private String uri;

    /**
     * 权重，通配符uri权重应低于完整uri
     */
    @TableField(value = "weight")
    private Integer weight;

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