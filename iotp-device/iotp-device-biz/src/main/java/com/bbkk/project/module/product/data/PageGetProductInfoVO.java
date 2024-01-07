package com.bbkk.project.module.product.data;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 分页查询产品信息 vo
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-12 19:33
 **/
@Data
@Builder
public class PageGetProductInfoVO {

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
     * 产品分类
     */
    private String type;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}
