package com.bbkk.project.module.tsl.convert;

import com.bbkk.project.module.tsl.data.OperateTslMethodParamsDTO;
import com.bbkk.project.module.tsl.entity.TslMethodParams;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

/**
 * 物模型方法输入/输出参数 转换器
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-06 20:56
 **/
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TslMethodParamsConvert {

    TslMethodParamsConvert INSTANCE = Mappers.getMapper(TslMethodParamsConvert.class);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "description", source = "dto.description")
    @Mapping(target = "dataType", source = "dto.dataType")
    @Mapping(target = "minValue", source = "dto.minValue")
    @Mapping(target = "maxValue", source = "dto.maxValue")
    @Mapping(target = "stepSize", source = "dto.stepSize")
    @Mapping(target = "unit", source = "dto.unit")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "methodId", ignore = true)
    TslMethodParams operateTslMethodParamsDto2TslMethodParams(OperateTslMethodParamsDTO dto, String type);

}
