package com.bbkk.project.module.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bbkk.project.module.product.data.PageGetProductTypeParams;
import com.bbkk.project.module.product.entity.ProductType;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-12 18:46
 **/
public interface IProductTypeService extends IService<ProductType> {

    /**
     * 根据 PageGetProductTypeParams 分页查询产品类目
     *
     * @param params PageGetProductTypeParams
     * @return IPage<ProductType>
     */
    IPage<ProductType> pageGetProductType(PageGetProductTypeParams params);
}
