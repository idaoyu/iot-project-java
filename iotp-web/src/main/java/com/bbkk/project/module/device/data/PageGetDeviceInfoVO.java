package com.bbkk.project.module.device.data;

import com.bbkk.project.module.product.entity.ProductInfo;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 分页查询设备信息 vo
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-16 11:13
 **/
@Data
@Builder
public class PageGetDeviceInfoVO {

    private String id;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 设备名字
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 产品信息
     */
    private ProductInfo productInfo;

}
