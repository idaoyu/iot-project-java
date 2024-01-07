package com.bbkk.project.module.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbkk.project.module.product.data.PageGetProductTypeParams;
import com.bbkk.project.module.product.entity.ProductType;
import com.bbkk.project.module.product.mapper.ProductTypeMapper;
import com.bbkk.project.module.product.service.IProductTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-12 18:46
 **/
@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {

    @Override
    public IPage<ProductType> pageGetProductType(PageGetProductTypeParams params) {
        LambdaQueryWrapper<ProductType> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StringUtils.isNotBlank(params.getName()), ProductType::getName, params.getName());
        return super.page(Page.of(params.getCurrent(), params.getPageSize()), wrapper);
    }
}
