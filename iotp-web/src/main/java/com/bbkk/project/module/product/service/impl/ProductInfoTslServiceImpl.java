package com.bbkk.project.module.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbkk.project.module.product.entity.ProductInfoTsl;
import com.bbkk.project.module.product.mapper.ProductInfoTslMapper;
import com.bbkk.project.module.product.service.IProductInfoTslService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-12 20:34
 **/
@Service
public class ProductInfoTslServiceImpl extends ServiceImpl<ProductInfoTslMapper, ProductInfoTsl> implements IProductInfoTslService {

    @Override
    public boolean removeByProductId(Long productId) {
        LambdaQueryWrapper<ProductInfoTsl> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ProductInfoTsl::getProductId, productId);
        boolean exists = super.exists(wrapper);
        if (!exists) {
            return true;
        }
        return super.remove(wrapper);
    }

    @Override
    public List<ProductInfoTsl> listByProductId(Long productId) {
        LambdaQueryWrapper<ProductInfoTsl> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ProductInfoTsl::getProductId, productId);
        return super.list(wrapper);
    }
}
