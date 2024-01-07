package com.bbkk.project.module.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 产品信息与物模型关联表
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023/12/12 20:34
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "product_info_tsl")
public class ProductInfoTsl {

    /**
     * 产品id
     */
    @TableField(value = "product_id")
    private Long productId;

    /**
     * 物模型id
     */
    @TableField(value = "tsl_id")
    private String tslId;

    /**
     * 物模型类型（属性、方法、事件）
     */
    @TableField(value = "tsl_type")
    private String tslType;
}