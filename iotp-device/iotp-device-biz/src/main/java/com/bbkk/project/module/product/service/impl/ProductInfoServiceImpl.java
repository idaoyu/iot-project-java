package com.bbkk.project.module.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbkk.project.module.product.data.PageGetProductInfoParams;
import com.bbkk.project.module.product.entity.ProductInfo;
import com.bbkk.project.module.product.mapper.ProductInfoMapper;
import com.bbkk.project.module.product.service.IProductInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-12 18:45
 **/
@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements IProductInfoService {

    @Override
    public IPage<ProductInfo> pageGetProductInfo(PageGetProductInfoParams params) {
        LambdaQueryWrapper<ProductInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StringUtils.isNotBlank(params.getName()), ProductInfo::getName, params.getName());
        return super.page(Page.of(params.getCurrent(), params.getPageSize()), wrapper);
    }
}
