package com.bbkk.project.module.tsl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 物模型方法输入/输出参数
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023/12/5 21:13
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tsl_method_params")
public class TslMethodParams {

    /**
     * 参数唯一标识
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 方法id
     */
    @TableField(value = "method_id")
    private String methodId;

    /**
     * 参数名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 参数描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 用来区分输入参数/输出参数
     */
    @TableField(value = "`type`")
    private String type;

    /**
     * 数据类型
     */
    @TableField(value = "data_type")
    private String dataType;

    /**
     * 最小值
     */
    @TableField(value = "min_value")
    private String minValue;

    /**
     * 最大值
     */
    @TableField(value = "max_value")
    private String maxValue;

    /**
     * 步长
     */
    @TableField(value = "step_size")
    private String stepSize;

    /**
     * 单位
     */
    @TableField(value = "unit")
    private String unit;

}