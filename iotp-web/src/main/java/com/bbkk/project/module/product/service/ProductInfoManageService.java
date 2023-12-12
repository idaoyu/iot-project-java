package com.bbkk.project.module.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bbkk.project.module.product.data.PageGetProductInfoParams;
import com.bbkk.project.module.product.data.PageGetProductInfoVO;
import com.bbkk.project.module.product.entity.ProductInfo;
import com.bbkk.project.module.product.entity.ProductType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 产品信息管理接口 业务逻辑
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-12 19:27
 **/
@Service
@RequiredArgsConstructor
public class ProductInfoManageService {

    private final IProductInfoService productInfoService;
    private final IProductTypeService productTypeService;

    public IPage<PageGetProductInfoVO> pageGetProductInfo(PageGetProductInfoParams params) {
        IPage<ProductInfo> page = productInfoService.pageGetProductInfo(params);
        return page.convert(v -> {
            PageGetProductInfoVO.PageGetProductInfoVOBuilder builder = PageGetProductInfoVO.builder();
            builder.id(v.getId());
            builder.name(v.getName());
            builder.description(v.getDescription());
            builder.imageUrl(v.getImageUrl());
            builder.createTime(v.getCreateTime());
            builder.updateTime(v.getUpdateTime());
            if (v.getType() != null) {
                ProductType productType = productTypeService.getById(v.getType());
                builder.type(productType != null ? productType.getName() : "其他");
            } else {
                builder.type("其他");
            }
            return builder.build();
        });
    }
}
