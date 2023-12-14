package com.bbkk.project.module.product.convert;

import com.bbkk.project.module.product.data.OperationProductInfoParams;
import com.bbkk.project.module.product.entity.ProductInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

/**
 * 产品信息 Convert
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-03 17:49
 **/
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductInfoConvert {

    ProductInfoConvert INSTANCE = Mappers.getMapper(ProductInfoConvert.class);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "imageUrl", target = "imageUrl")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "needAuth", target = "needAuth")
    @Mapping(source = "authType", target = "authType")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createTime", expression = "java(new java.util.Date())")
    @Mapping(target = "updateTime", expression = "java(new java.util.Date())")
    ProductInfo operationProductInfoParams2ProductInfo(OperationProductInfoParams params);


}
