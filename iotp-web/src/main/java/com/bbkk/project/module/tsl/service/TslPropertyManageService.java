package com.bbkk.project.module.tsl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bbkk.project.exception.BizException;
import com.bbkk.project.module.tsl.convert.TslPropertyConvert;
import com.bbkk.project.module.tsl.data.*;
import com.bbkk.project.module.tsl.entity.TslEnumValue;
import com.bbkk.project.module.tsl.entity.TslProperty;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.bbkk.project.module.tsl.constant.PropertyDataTypeConstant.ENUM;

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
        if (tslProperty.getDataType().equals(ENUM.getDataType())) {
            for (OperateTslEnumValueParams operateTslEnumValueParams : params.getEnumValueParamsList()) {
                TslEnumValue.TslEnumValueBuilder builder = TslEnumValue.builder();
                builder.value(operateTslEnumValueParams.getValue());
                builder.description(operateTslEnumValueParams.getDescription());
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
        if (tslProperty.getDataType().equals(ENUM.getDataType())) {
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
            if (record.getDataType().equals(ENUM.getDataType())) {
                // 数据类型为枚举
                List<TslEnumValue> enumValueList = tslEnumValueService.listByMasterId(record.getId());
                record.setEnumValueList(enumValueList);
            }
        }
        return voPage;
    }

    @Transactional(rollbackFor = Exception.class)
    public String updateTslProperty(UpdateTslPropertyParams params, String id) {
        TslProperty tslProperty = tslPropertyService.getOptById(id).orElseThrow(() -> new BizException("要修改的物模型属性不存在"));
        // 处理枚举类型
        handlerTslEnumValue(tslProperty, params.getDataType(), params.getEnumValueParamsList());
        if (!tslProperty.getDataType().equals(ENUM.getDataType()) && params.getDataType().equals(ENUM.getDataType())) {
            // 如果原来不是枚举 现在是枚举 清空 最大/最小值等
            params.setMaxValue(null);
            params.setMinValue(null);
            params.setStepSize(null);
            params.setUnit(null);
        }
        Boolean updateTslProperty = tslPropertyService.updateTslProperty(tslProperty, params, id);
        if (!updateTslProperty) {
            throw new BizException("修改物模型属性失败，请稍后重试");
        }
        return "成功";
    }

    /**
     * 处理物模型属性的枚举数据
     *
     * @param tslProperty         原物模型属性对象
     * @param newDataType         新的物模型数据类型
     * @param enumValueParamsList 新的物模型枚举
     */
    private void handlerTslEnumValue(TslProperty tslProperty, String newDataType, List<OperateTslEnumValueParams> enumValueParamsList) {
        if (ENUM.getDataType().equals(tslProperty.getDataType()) && !newDataType.equals(ENUM.getDataType())) {
            // 如果修改之前是枚举 修改后不是枚举
            Boolean success = tslEnumValueService.removeByMasterId(tslProperty.getId());
            if (!success) {
                throw new BizException("修改物模型属性失败，请稍后重试");
            }
            return;
        } else if (!ENUM.getDataType().equals(tslProperty.getDataType()) && newDataType.equals(ENUM.getDataType())) {
            // 如果修改之前不是枚举 修改后是枚举
            for (OperateTslEnumValueParams enumValueParams : enumValueParamsList) {
                TslEnumValue.TslEnumValueBuilder builder = TslEnumValue.builder();
                builder.masterId(tslProperty.getId());
                builder.value(enumValueParams.getValue());
                builder.description(enumValueParams.getDescription());
                boolean success = tslEnumValueService.save(builder.build());
                if (!success) {
                    throw new BizException("修改物模型属性失败，请稍后重试");
                }
            }
            return;
        } else if (!ENUM.getDataType().equals(tslProperty.getDataType()) && !newDataType.equals(ENUM.getDataType())) {
            // 原来不是现在也不是
            return;
        }
        // 类型没有修改 但是内容可能被修改
        List<TslEnumValue> list = tslEnumValueService.listByMasterId(tslProperty.getId());
        Map<String, TslEnumValue> map = list.stream().collect(Collectors.toMap(TslEnumValue::getValue, v -> v));
        List<String> newValueList = Lists.newArrayList();
        for (OperateTslEnumValueParams enumValueParams : enumValueParamsList) {
            TslEnumValue tslEnumValue = map.get(enumValueParams.getValue());
            if (tslEnumValue == null) {
                // 原数据中不包含新数据 则新增
                TslEnumValue.TslEnumValueBuilder builder = TslEnumValue.builder();
                builder.masterId(tslProperty.getId());
                builder.value(enumValueParams.getValue());
                builder.description(enumValueParams.getDescription());
                boolean success = tslEnumValueService.save(builder.build());
                if (!success) {
                    throw new BizException("修改物模型属性失败，请稍后重试");
                }
            } else if (!tslEnumValue.getDescription().equals(enumValueParams.getDescription())) {
                // 如果 value 相同 但 description 不相同 则更新 description
                Boolean success = tslEnumValueService.updateDescriptionById(enumValueParams.getDescription(), tslEnumValue.getId());
                if (!success) {
                    throw new BizException("修改物模型属性失败，请稍后重试");
                }
            }
            newValueList.add(enumValueParams.getValue());
        }
        // 过滤出需要删除的
        List<TslEnumValue> waitRemoveEnumValueList = list.stream().filter(v -> !newValueList.contains(v.getValue())).toList();
        if (!waitRemoveEnumValueList.isEmpty()) {
            boolean success = tslEnumValueService.
                    removeBatchByIds(waitRemoveEnumValueList.stream().map(TslEnumValue::getId).toList());
            if (!success) {
                throw new BizException("修改物模型属性失败，请稍后重试");
            }
        }
    }
}
