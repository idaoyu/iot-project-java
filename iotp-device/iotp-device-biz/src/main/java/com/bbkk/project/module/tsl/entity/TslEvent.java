package com.bbkk.project.module.tsl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 物模型事件 entity
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023/12/11 18:38
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tsl_event")
public class TslEvent {

    /**
     * 唯一标识
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 事件类型
     */
    @TableField(value = "`type`")
    private String type;

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