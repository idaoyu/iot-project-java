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
 * 物模型属性
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023/12/3 13:55
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tsl_property")
public class TslProperty {

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
     * 数据类型（int:整数 float:浮点数 enum:枚举）
     */
    @TableField(value = "data_type")
    private String dataType;

    /**
     * 最大值，在dataType为数值型时有效
     */
    @TableField(value = "max_value")
    private String maxValue;

    /**
     * 最小值，在dataType为数值型时有效
     */
    @TableField(value = "min_value")
    private String minValue;

    /**
     * 步长
     */
    @TableField(value = "step_size")
    private Long stepSize;

    /**
     * 单位
     */
    @TableField(value = "unit")
    private String unit;

    /**
     * 是否是只读属性
     */
    @TableField(value = "only_read")
    private Boolean onlyRead;

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