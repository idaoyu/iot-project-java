package com.bbkk.project.module.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bbkk.project.module.product.data.OperationProductInfoParams;
import com.bbkk.project.module.product.data.PageGetProductInfoParams;
import com.bbkk.project.module.product.data.PageGetProductInfoVO;
import com.bbkk.project.module.product.service.ProductInfoManageService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 产品信息管理接口
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-12 18:42
 **/
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manage/productInfo")
public class ProductInfoManageController {

    private final ProductInfoManageService productInfoManageService;

    @GetMapping
    public IPage<PageGetProductInfoVO> pageGetProductInfo(@Validated PageGetProductInfoParams params) {
        return productInfoManageService.pageGetProductInfo(params);
    }

    @PostMapping
    public String createProductInfo(@RequestBody @Validated OperationProductInfoParams params) {
        return productInfoManageService.createProductInfo(params);
    }

    @DeleteMapping
    public String deleteProductInfo(@NotNull(message = "id不能为空") Long id) {
        return productInfoManageService.deleteProductInfo(id);
    }

}
