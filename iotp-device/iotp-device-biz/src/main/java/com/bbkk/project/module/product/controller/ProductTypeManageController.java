package com.bbkk.project.module.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bbkk.project.module.product.data.OperationProductTypeParams;
import com.bbkk.project.module.product.data.PageGetProductTypeParams;
import com.bbkk.project.module.product.entity.ProductType;
import com.bbkk.project.module.product.service.ProductTypeManageService;
import com.bbkk.project.utils.ValidatedGroup;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 产品类目管理接口
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-12 18:47
 **/
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manage/productType")
public class ProductTypeManageController {

    private final ProductTypeManageService productTypeManageService;

    @GetMapping
    public IPage<ProductType> pageGetProductType(@Validated PageGetProductTypeParams params) {
        return productTypeManageService.pageGetProductType(params);
    }

    @PostMapping
    public String createProductType(@RequestBody @Validated OperationProductTypeParams params) {
        return productTypeManageService.createProductType(params);
    }

    @PutMapping
    public String updateProductType(@RequestBody @Validated(ValidatedGroup.UpdateGroup.class) OperationProductTypeParams params) {
        return productTypeManageService.updateProductType(params);
    }

    @DeleteMapping
    public String removeProductType(@NotNull(message = "要删除的产品列目id不能为空") Long id) {
        return productTypeManageService.removeProductType(id);
    }

}
