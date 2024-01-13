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
 * 产品信息表
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023/12/12 18:44
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "product_info")
public class ProductInfo {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 产品名字
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 产品描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 产品图片地址
     */
    @TableField(value = "image_url")
    private String imageUrl;

    /**
     * 产品分类（存放分类表id）
     */
    @TableField(value = "`type`")
    private Long type;

    /**
     * 是否需要存储设备上报的属性
     */
    @TableField(value = "need_save_property")
    private Boolean needSaveProperty;

    /**
     * 需要认证
     */
    @TableField(value = "need_auth")
    private Boolean needAuth;

    /**
     * 认证类型
     */
    @TableField(value = "auth_type")
    private String authType;

    /**
     * 产品状态
     */
    @TableField(value = "`status`")
    private String status;

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