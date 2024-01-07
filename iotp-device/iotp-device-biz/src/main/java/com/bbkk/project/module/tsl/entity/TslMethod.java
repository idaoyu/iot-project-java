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
 * 物模型方法
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023/12/5 21:12
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tsl_method")
public class TslMethod {

    /**
     * 方法唯一标识
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 方法名字
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 是异步调用
     */
    @TableField(value = "asynchronous")
    private Boolean asynchronous;

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