package com.bbkk.project.module.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bbkk.project.module.product.data.PageGetProductInfoParams;
import com.bbkk.project.module.product.data.PageGetProductInfoVO;
import com.bbkk.project.module.product.service.ProductInfoManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 产品信息管理接口
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-12 18:42
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manage/productInfo")
public class ProductInfoManageController {

    private final ProductInfoManageService productInfoManageService;

    @GetMapping
    public IPage<PageGetProductInfoVO> pageGetProductInfo(@Validated PageGetProductInfoParams params) {
        return productInfoManageService.pageGetProductInfo(params);
    }

}
