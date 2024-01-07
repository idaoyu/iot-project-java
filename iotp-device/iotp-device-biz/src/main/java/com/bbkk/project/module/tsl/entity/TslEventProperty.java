package com.bbkk.project.module.tsl.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 物模型事件与属性关联表 entity
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023/12/11 18:39
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tsl_event_property")
public class TslEventProperty {

    /**
     * 事件id
     */
    @TableField(value = "event_id")
    private String eventId;

    /**
     * 属性id
     */
    @TableField(value = "property_id")
    private String propertyId;
}