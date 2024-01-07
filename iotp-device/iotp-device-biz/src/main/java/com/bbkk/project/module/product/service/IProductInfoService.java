package com.bbkk.project.module.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bbkk.project.module.product.data.PageGetProductInfoParams;
import com.bbkk.project.module.product.entity.ProductInfo;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-12 18:45
 **/
public interface IProductInfoService extends IService<ProductInfo> {

    /**
     * 分页查询 产品信息
     *
     * @param params PageGetProductInfoParams
     * @return IPage<ProductInfo>
     */
    IPage<ProductInfo> pageGetProductInfo(PageGetProductInfoParams params);
}
