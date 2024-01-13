package com.bbkk.project.module.product.service;

import com.bbkk.project.data.GetProductInfoDTO;
import com.bbkk.project.data.R;
import com.bbkk.project.exception.BizException;
import com.bbkk.project.module.product.convert.ProductInfoConvert;
import com.bbkk.project.module.product.entity.ProductInfo;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import static com.bbkk.project.constant.RedisCacheKeyConstant.IOT_PRODUCT_INFO_CACHE;

/**
 * 产品信息接口 业务逻辑
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-13 18:14
 **/
@Service
@RequiredArgsConstructor
public class ProductInfoService {

    private final IProductInfoService productInfoService;
    private final RedissonClient redissonClient;
    private final ProductInfoConvert productInfoConvert;

    public R<GetProductInfoDTO> getProductInfo(Long id) {
        // 尝试通过 redis 缓存获取
        RMap<Long, GetProductInfoDTO> map = redissonClient.getMap(IOT_PRODUCT_INFO_CACHE.getKey());
        GetProductInfoDTO getProductInfoDTO = map.get(id);
        if (getProductInfoDTO != null) {
            return R.ok(getProductInfoDTO);
        }
        ProductInfo productInfo = productInfoService.getOptById(id).orElseThrow(() -> new BizException("产品不存在"));
        getProductInfoDTO = productInfoConvert.productInfo2GetProductInfoDTO(productInfo);
        map.put(id, getProductInfoDTO);
        return R.ok(getProductInfoDTO);
    }
}
