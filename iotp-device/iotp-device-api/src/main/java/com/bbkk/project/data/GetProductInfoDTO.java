package com.bbkk.project.data;

import lombok.Data;

import java.util.Date;

/**
 * 获取产品信息 dto
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-13 18:13
 **/
@Data
public class GetProductInfoDTO {

    private Long id;

    /**
     * 产品名字
     */
    private String name;

    /**
     * 产品描述
     */
    private String description;

    /**
     * 产品图片地址
     */
    private String imageUrl;

    /**
     * 产品分类（存放分类表id）
     */
    private Long type;

    /**
     * 是否需要存储设备上报的属性
     */
    private Boolean needSaveProperty;

    /**
     * 需要认证
     */
    private Boolean needAuth;

    /**
     * 认证类型
     */
    private String authType;

    /**
     * 产品状态
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}
