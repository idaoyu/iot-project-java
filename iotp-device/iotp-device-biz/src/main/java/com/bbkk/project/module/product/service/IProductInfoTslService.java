package com.bbkk.project.module.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bbkk.project.module.product.entity.ProductInfoTsl;

import java.util.List;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-12 20:34
 **/
public interface IProductInfoTslService extends IService<ProductInfoTsl> {

    /**
     * 根据产品id删除关联记录
     *
     * @param productId 产品id
     * @return 成功返回 true
     */
    boolean removeByProductId(Long productId);

    /**
     * 根据产品id查询关联记录
     *
     * @param productId 产品id
     * @return List<ProductInfoTsl>
     */
    List<ProductInfoTsl> listByProductId(Long productId);

}
