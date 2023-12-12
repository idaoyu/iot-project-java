package com.bbkk.project.module.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbkk.project.module.product.entity.ProductInfo;
import com.bbkk.project.module.product.mapper.ProductInfoMapper;
import com.bbkk.project.module.product.service.IProductInfoService;
import org.springframework.stereotype.Service;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-12 18:45
 **/
@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements IProductInfoService {
}
