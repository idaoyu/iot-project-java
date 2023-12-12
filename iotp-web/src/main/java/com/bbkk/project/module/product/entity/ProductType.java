package com.bbkk.project.module.product.entity;

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
 * 产品类目
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023/12/12 18:44
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "product_type")
public class ProductType {

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 类目名字
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 类目描述
     */
    @TableField(value = "description")
    private String description;

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