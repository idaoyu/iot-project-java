package com.bbkk.project.module.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bbkk.project.exception.BizException;
import com.bbkk.project.module.device.service.IDeviceEvidencePoolService;
import com.bbkk.project.module.product.constant.ProductAuthType;
import com.bbkk.project.module.product.constant.TslTypeConstant;
import com.bbkk.project.module.product.convert.ProductInfoConvert;
import com.bbkk.project.module.product.data.OperationProductInfoParams;
import com.bbkk.project.module.product.data.PageGetProductInfoParams;
import com.bbkk.project.module.product.data.PageGetProductInfoVO;
import com.bbkk.project.module.product.data.ProductInfoAndTslDTO;
import com.bbkk.project.module.product.entity.ProductInfo;
import com.bbkk.project.module.product.entity.ProductInfoTsl;
import com.bbkk.project.module.product.entity.ProductType;
import com.bbkk.project.module.tsl.service.ITslEventService;
import com.bbkk.project.module.tsl.service.ITslMethodService;
import com.bbkk.project.module.tsl.service.ITslPropertyService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
    private final IProductInfoTslService productInfoTslService;
    private final ITslPropertyService tslPropertyService;
    private final ITslMethodService tslMethodService;
    private final ITslEventService tslEventService;
    private final IDeviceEvidencePoolService deviceEvidencePoolService;

    private final ProductInfoConvert productInfoConvert;

    public IPage<PageGetProductInfoVO> pageGetProductInfo(PageGetProductInfoParams params) {
        // todo 待重构 需要考虑物模型、认证方式等数据的展示方法
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

    @Transactional(rollbackFor = Exception.class)
    public String createProductInfo(OperationProductInfoParams params) {
        ProductInfo productInfo = productInfoConvert.operationProductInfoParams2ProductInfo(params);
        if (params.getType() != null) {
            ProductType productType = productTypeService.getOptById(params.getType()).orElseThrow(() -> new BizException("产品类目错误"));
            productInfo.setType(productType.getId());
        }
        // 默认的状态就是开发中，除非用户手动指定状态
        if (StringUtils.isNotBlank(params.getStatus())) {
            productInfo.setStatus(params.getStatus());
        } else {
            // todo 懒得写枚举了，如果后续状态有业务逻辑意义再写吧
            productInfo.setStatus("dev");
        }
        boolean save = productInfoService.save(productInfo);
        if (!save) {
            throw new BizException("创建产品失败，请稍后重试");
        }
        // 需要认证，并且认证类型为一个产品一个密钥时，在创建产品时生成密钥
        if (productInfo.getNeedAuth() && productInfo.getAuthType().equals(ProductAuthType.BIND_PRODUCT.getType())) {
            deviceEvidencePoolService.generateAuthKeyAndBind(productInfo.getId(), null);
        }
        for (ProductInfoAndTslDTO productInfoAndTslDTO : params.getTslList()) {
            String type = productInfoAndTslDTO.getType();
            String tslId = productInfoAndTslDTO.getTslId();
            if (type.equals(TslTypeConstant.PROPERTY.getValue())) {
                tslPropertyService.getOptById(tslId).orElseThrow(() -> new BizException("物模型属性id：" + tslId + "不存在"));
                ProductInfoTsl productInfoTsl = ProductInfoTsl
                        .builder()
                        .tslId(tslId)
                        .tslType(TslTypeConstant.PROPERTY.getValue())
                        .productId(productInfo.getId())
                        .build();
                boolean saveInfoTsl = productInfoTslService.save(productInfoTsl);
                if (!saveInfoTsl) {
                    throw new BizException("创建产品失败，请稍后重试");
                }
            } else if (type.equals(TslTypeConstant.METHOD.getValue())) {
                tslMethodService.getOptById(tslId).orElseThrow(() -> new BizException("物模型方法id：" + tslId + "不存在"));
                ProductInfoTsl productInfoTsl = ProductInfoTsl
                        .builder()
                        .tslId(tslId)
                        .tslType(TslTypeConstant.METHOD.getValue())
                        .productId(productInfo.getId())
                        .build();
                boolean saveInfoTsl = productInfoTslService.save(productInfoTsl);
                if (!saveInfoTsl) {
                    throw new BizException("创建产品失败，请稍后重试");
                }
            } else if (type.equals(TslTypeConstant.EVENT.getValue())) {
                tslEventService.getOptById(tslId).orElseThrow(() -> new BizException("物模型事件id：" + tslId + "不存在"));
                ProductInfoTsl productInfoTsl = ProductInfoTsl
                        .builder()
                        .tslId(tslId)
                        .tslType(TslTypeConstant.EVENT.getValue())
                        .productId(productInfo.getId())
                        .build();
                boolean saveInfoTsl = productInfoTslService.save(productInfoTsl);
                if (!saveInfoTsl) {
                    throw new BizException("创建产品失败，请稍后重试");
                }
            } else {
                throw new BizException("绑定的物模型类别字段错误");
            }
        }
        return "成功";
    }

    @Transactional(rollbackFor = Exception.class)
    public String deleteProductInfo(Long id) {
        productInfoService.getOptById(id).orElseThrow(() -> new BizException("要删除的产品不存在"));
        boolean rmInfo = productInfoService.removeById(id);
        if (!rmInfo) {
            throw new BizException("删除产品失败，请稍后重试");
        }
        boolean rmInfoTsl = productInfoTslService.removeByProductId(id);
        if (!rmInfoTsl) {
            throw new BizException("删除产品失败，请稍后重试");
        }
        // todo 需要增加删除设备认证凭据表（device_evidence_pool）数据
        return "成功";
    }

    @Transactional(rollbackFor = Exception.class)
    public String updateProductInfo(OperationProductInfoParams params) {
        // todo 暂时不支持修改是否需要认证和认证类型 后期增加用 mq 异步方式平滑修改
        ProductInfo productInfo = productInfoService.getOptById(params.getId()).orElseThrow(() -> new BizException("要修改的产品不存在"));
        productInfo.setName(params.getName());
        productInfo.setDescription(params.getDescription());
        productInfo.setImageUrl(params.getImageUrl());
        if (params.getType() != null) {
            ProductType productType = productTypeService.getOptById(params.getType()).orElseThrow(() -> new BizException("商品类目不存在"));
            productInfo.setType(productType.getId());
        }
        productInfo.setUpdateTime(new Date());
        boolean update = productInfoService.updateById(productInfo);
        if (!update) {
            throw new BizException("修改产品信息失败，请稍后重试");
        }
        boolean rmSuccess = productInfoTslService.removeByProductId(params.getId());
        if (!rmSuccess) {
            throw new BizException("修改产品信息失败，请稍后重试");
        }
        for (ProductInfoAndTslDTO productInfoAndTslDTO : params.getTslList()) {
            String type = productInfoAndTslDTO.getType();
            String tslId = productInfoAndTslDTO.getTslId();
            if (type.equals(TslTypeConstant.PROPERTY.getValue())) {
                tslPropertyService.getOptById(tslId).orElseThrow(() -> new BizException("物模型属性id：" + tslId + "不存在"));
                ProductInfoTsl productInfoTsl = ProductInfoTsl
                        .builder()
                        .tslId(tslId)
                        .tslType(TslTypeConstant.PROPERTY.getValue())
                        .productId(productInfo.getId())
                        .build();
                boolean saveInfoTsl = productInfoTslService.save(productInfoTsl);
                if (!saveInfoTsl) {
                    throw new BizException("修改产品失败，请稍后重试");
                }
            } else if (type.equals(TslTypeConstant.METHOD.getValue())) {
                tslMethodService.getOptById(tslId).orElseThrow(() -> new BizException("物模型方法id：" + tslId + "不存在"));
                ProductInfoTsl productInfoTsl = ProductInfoTsl
                        .builder()
                        .tslId(tslId)
                        .tslType(TslTypeConstant.METHOD.getValue())
                        .productId(productInfo.getId())
                        .build();
                boolean saveInfoTsl = productInfoTslService.save(productInfoTsl);
                if (!saveInfoTsl) {
                    throw new BizException("修改产品失败，请稍后重试");
                }
            } else if (type.equals(TslTypeConstant.EVENT.getValue())) {
                tslEventService.getOptById(tslId).orElseThrow(() -> new BizException("物模型事件id：" + tslId + "不存在"));
                ProductInfoTsl productInfoTsl = ProductInfoTsl
                        .builder()
                        .tslId(tslId)
                        .tslType(TslTypeConstant.EVENT.getValue())
                        .productId(productInfo.getId())
                        .build();
                boolean saveInfoTsl = productInfoTslService.save(productInfoTsl);
                if (!saveInfoTsl) {
                    throw new BizException("修改产品失败，请稍后重试");
                }
            } else {
                throw new BizException("绑定的物模型类别字段错误");
            }
        }

        return "成功";
    }
}
