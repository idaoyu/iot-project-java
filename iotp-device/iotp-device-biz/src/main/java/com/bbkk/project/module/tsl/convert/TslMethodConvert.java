package com.bbkk.project.module.tsl.convert;

import com.bbkk.project.module.tsl.data.OperateTslMethodParamsDTO;
import com.bbkk.project.module.tsl.data.PageGetTslMethodParamsVO;
import com.bbkk.project.module.tsl.data.PageGetTslMethodVO;
import com.bbkk.project.module.tsl.entity.TslMethod;
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
public interface TslMethodConvert {

    TslMethodConvert INSTANCE = Mappers.getMapper(TslMethodConvert.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "identifier", source = "dto.identifier")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "description", source = "dto.description")
    @Mapping(target = "dataType", source = "dto.dataType")
    @Mapping(target = "minValue", source = "dto.minValue")
    @Mapping(target = "maxValue", source = "dto.maxValue")
    @Mapping(target = "stepSize", source = "dto.stepSize")
    @Mapping(target = "unit", source = "dto.unit")
    @Mapping(target = "type", source = "dto.type")
    @Mapping(target = "methodId", ignore = true)
    TslMethodParams operateTslMethodParamsDto2TslMethodParams(OperateTslMethodParamsDTO dto);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "asynchronous", source = "asynchronous")
    @Mapping(target = "createTime", source = "createTime")
    @Mapping(target = "paramsList", ignore = true)
    PageGetTslMethodVO tslMethod2PageGetTslMethodVO(TslMethod tslMethod);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "dataType", source = "dataType")
    @Mapping(target = "minValue", source = "minValue")
    @Mapping(target = "maxValue", source = "maxValue")
    @Mapping(target = "stepSize", source = "stepSize")
    @Mapping(target = "unit", source = "unit")
    @Mapping(target = "enumValueList", ignore = true)
    PageGetTslMethodParamsVO tslMethodParams2PageGetTslMethodParamsVO(TslMethodParams tslMethodParams);

}
