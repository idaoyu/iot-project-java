package com.bbkk.project.module.tsl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bbkk.project.exception.BizException;
import com.bbkk.project.module.tsl.constant.PropertyDataTypeConstant;
import com.bbkk.project.module.tsl.convert.TslPropertyConvert;
import com.bbkk.project.module.tsl.data.CreateTslPropertyParams;
import com.bbkk.project.module.tsl.data.PageGetTslPropertyParams;
import com.bbkk.project.module.tsl.data.TslEnumValueParams;
import com.bbkk.project.module.tsl.data.TslPropertyVO;
import com.bbkk.project.module.tsl.entity.TslEnumValue;
import com.bbkk.project.module.tsl.entity.TslProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 物模型属性管理接口 业务逻辑
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-03 13:59
 **/
@Service
@RequiredArgsConstructor
public class TslPropertyManageService {

    private final ITslPropertyService tslPropertyService;
    private final ITslEnumValueService tslEnumValueService;
    private final TslPropertyConvert tslPropertyConvert;

    @Transactional(rollbackFor = Exception.class)
    public String createTslProperty(CreateTslPropertyParams params) {
        TslProperty property = tslPropertyService.getById(params.getId());
        if (property != null) {
            throw new BizException("唯一标识已存在");
        }
        TslProperty tslProperty = tslPropertyConvert.createTslPropertyParams2TslProperty(params);
        boolean success = tslPropertyService.save(tslProperty);
        if (!success) {
            throw new BizException("创建物模型属性失败，请稍后重试");
        }
        if (tslProperty.getDataType().equals(PropertyDataTypeConstant.ENUM.getDataType())) {
            for (TslEnumValueParams tslEnumValueParams : params.getEnumValueParamsList()) {
                TslEnumValue.TslEnumValueBuilder builder = TslEnumValue.builder();
                builder.value(tslEnumValueParams.getValue());
                builder.description(tslEnumValueParams.getDescription());
                builder.masterId(tslProperty.getId());
                boolean saveEnumValue = tslEnumValueService.save(builder.build());
                if (!saveEnumValue) {
                    throw new BizException("创建物模型属性失败，请稍后重试");
                }
            }
        }

        return "成功";
    }

    @Transactional(rollbackFor = Exception.class)
    public String removeTslProperty(String id) {
        TslProperty tslProperty = tslPropertyService.getOptById(id).orElseThrow(() -> new BizException("要删除的物模型属性不存在"));
        if (tslProperty.getDataType().equals(PropertyDataTypeConstant.ENUM.getDataType())) {
            Boolean rmEnumValue = tslEnumValueService.removeByMasterId(id);
            if (!rmEnumValue) {
                throw new BizException("删除物模型属性失败，请稍后重试");
            }
        }
        boolean rmProperty = tslPropertyService.removeById(id);
        if (!rmProperty) {
            throw new BizException("删除物模型属性失败，请稍后重试");
        }
        return "成功";
    }

    public IPage<TslPropertyVO> pageGetTslProperty(PageGetTslPropertyParams params) {
        IPage<TslProperty> page = tslPropertyService.pageByParams(params);
        // TslProperty -> TslPropertyVO
        IPage<TslPropertyVO> voPage = page.convert(tslPropertyConvert::tslProperty2TslPropertyVO);
        for (TslPropertyVO record : voPage.getRecords()) {
            if (record.getDataType().equals(PropertyDataTypeConstant.ENUM.getDataType())) {
                // 数据类型为枚举
                List<TslEnumValue> enumValueList = tslEnumValueService.listByMasterId(record.getId());
                record.setEnumValueList(enumValueList);
            }
        }
        return voPage;
    }
}
