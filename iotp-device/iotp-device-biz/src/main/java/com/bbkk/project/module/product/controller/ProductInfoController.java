package com.bbkk.project.module.product.controller;

import com.bbkk.project.data.GetProductInfoDTO;
import com.bbkk.project.data.R;
import com.bbkk.project.module.product.service.ProductInfoService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 产品信息接口
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-13 18:01
 **/
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/productInfo")
public class ProductInfoController {

    private final ProductInfoService productInfoService;

    @GetMapping("/get")
    public R<GetProductInfoDTO> getProductInfo(@RequestParam("id") @NotNull(message = "产品id不能为空") Long id) {
        return productInfoService.getProductInfo(id);
    }

}
