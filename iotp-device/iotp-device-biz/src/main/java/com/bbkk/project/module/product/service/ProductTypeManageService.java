package com.bbkk.project.module.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bbkk.project.exception.BizException;
import com.bbkk.project.module.product.data.OperationProductTypeParams;
import com.bbkk.project.module.product.data.PageGetProductTypeParams;
import com.bbkk.project.module.product.entity.ProductType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 产品类目管理接口 业务逻辑
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-12 18:48
 **/
@Service
@RequiredArgsConstructor
public class ProductTypeManageService {

    private final IProductTypeService productTypeService;

    public IPage<ProductType> pageGetProductType(PageGetProductTypeParams params) {
        return productTypeService.pageGetProductType(params);
    }

    public String createProductType(OperationProductTypeParams params) {
        ProductType.ProductTypeBuilder builder = ProductType.builder();
        builder.name(params.getName());
        builder.description(params.getDescription());
        builder.createTime(new Date());
        builder.updateTime(new Date());
        boolean save = productTypeService.save(builder.build());
        if (!save) {
            throw new BizException("创建类目时出现错误，请稍后重试");
        }
        return "成功";
    }

    public String updateProductType(OperationProductTypeParams params) {
        ProductType productType = productTypeService.getOptById(params.getId())
                .orElseThrow(() -> new BizException("要修改的产品类目不存在"));
        productType.setName(params.getName());
        productType.setDescription(params.getDescription());
        productType.setUpdateTime(new Date());
        boolean update = productTypeService.updateById(productType);
        if (!update) {
            throw new BizException("修改类目时出现错误，请稍后重试");
        }
        return "成功";
    }

    public String removeProductType(Long id) {
        productTypeService.getOptById(id).orElseThrow(() -> new BizException("要删除的产品类目不存在"));
        boolean success = productTypeService.removeById(id);
        if (!success) {
            throw new BizException("删除类目时出现错误，请稍后重试");
        }
        return "成功";
    }
}
