package com.bbkk.project.module.tsl.convert;

import com.bbkk.project.module.tsl.data.CreateTslPropertyParams;
import com.bbkk.project.module.tsl.data.TslPropertyVO;
import com.bbkk.project.module.tsl.entity.TslProperty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

/**
 * 物模型属性 转换
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-03 17:49
 **/
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TslPropertyConvert {

    TslPropertyConvert INSTANCE = Mappers.getMapper(TslPropertyConvert.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "dataType", source = "dataType")
    @Mapping(target = "maxValue", source = "maxValue")
    @Mapping(target = "minValue", source = "minValue")
    @Mapping(target = "stepSize", source = "stepSize")
    @Mapping(target = "unit", source = "unit")
    @Mapping(target = "onlyRead", source = "onlyRead")
    @Mapping(target = "createTime", expression = "java(new java.util.Date())")
    @Mapping(target = "updateTime", expression = "java(new java.util.Date())")
    TslProperty createTslPropertyParams2TslProperty(CreateTslPropertyParams params);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "dataType", source = "dataType")
    @Mapping(target = "maxValue", source = "maxValue")
    @Mapping(target = "minValue", source = "minValue")
    @Mapping(target = "stepSize", source = "stepSize")
    @Mapping(target = "unit", source = "unit")
    @Mapping(target = "onlyRead", source = "onlyRead")
    @Mapping(target = "createTime", source = "createTime")
    @Mapping(target = "updateTime", source = "updateTime")
    @Mapping(target = "enumValueList", ignore = true)
    TslPropertyVO tslProperty2TslPropertyVO(TslProperty tslProperty);


}
