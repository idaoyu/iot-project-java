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
 * 物模型枚举值
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023/12/3 13:54
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tsl_enum_value")
public class TslEnumValue {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关联的属性/方法/事件的id
     */
    @TableField(value = "master_id")
    private String masterId;

    /**
     * 值
     */
    @TableField(value = "value")
    private String value;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;
}