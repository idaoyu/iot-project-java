package com.bbkk.project.module.tsl.service;

import com.bbkk.project.exception.BizException;
import com.bbkk.project.module.tsl.constant.DataTypeConstant;
import com.bbkk.project.module.tsl.constant.EnumValueSourceConstant;
import com.bbkk.project.module.tsl.convert.TslMethodParamsConvert;
import com.bbkk.project.module.tsl.data.CreateTslMethodParams;
import com.bbkk.project.module.tsl.data.OperateTslEnumValueParams;
import com.bbkk.project.module.tsl.data.OperateTslMethodParamsDTO;
import com.bbkk.project.module.tsl.entity.TslEnumValue;
import com.bbkk.project.module.tsl.entity.TslMethod;
import com.bbkk.project.module.tsl.entity.TslMethodParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 物模型方法管理接口 业务逻辑
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-05 21:20
 **/
@Service
@RequiredArgsConstructor
public class TslMethodManageService {

    private final ITslMethodService tslMethodService;
    private final ITslMethodParamsService tslMethodParamsService;
    private final ITslEnumValueService tslEnumValueService;
    private final TslMethodParamsConvert tslMethodParamsConvert;

    @Transactional(rollbackFor = Exception.class)
    public String createTslMethod(CreateTslMethodParams params) {
        TslMethod tslMethod = createTslMethodParams2TslMethod(params);
        boolean saveMethod = tslMethodService.save(tslMethod);
        if (!saveMethod) {
            throw new BizException("创建物模型方法失败，请稍后重试");
        }
        for (OperateTslMethodParamsDTO methodParams : params.getInputParams()) {
            save(tslMethod.getId(), methodParams, "input");
        }
        for (OperateTslMethodParamsDTO methodParams : params.getOutputParams()) {
            save(tslMethod.getId(), methodParams, "output");
        }
        return "成功";
    }

    private void save(String methodId, OperateTslMethodParamsDTO methodParams, String type) {
        TslMethodParams tslMethodParams = tslMethodParamsConvert
                .operateTslMethodParamsDto2TslMethodParams(methodParams, type);
        tslMethodParams.setMethodId(methodId);
        boolean saveMethodParams = tslMethodParamsService.save(tslMethodParams);
        if (!saveMethodParams) {
            throw new BizException("创建物模型方法失败，请稍后重试");
        }
        if (methodParams.getDataType().equals(DataTypeConstant.ENUM.getDataType())) {
            // 如果是枚举类型
            for (OperateTslEnumValueParams enumValueParams : methodParams.getEnumValueParamsList()) {
                TslEnumValue.TslEnumValueBuilder builder = TslEnumValue.builder();
                builder.masterId(tslMethodParams.getId());
                builder.source(EnumValueSourceConstant.METHOD_PARAMS.getSource());
                builder.value(enumValueParams.getValue());
                builder.description(enumValueParams.getDescription());
                boolean saveEnumValue = tslEnumValueService.save(builder.build());
                if (!saveEnumValue) {
                    throw new BizException("创建物模型方法失败，请稍后重试");
                }
            }
        }
    }

    private TslMethod createTslMethodParams2TslMethod(CreateTslMethodParams params) {
        TslMethod.TslMethodBuilder builder = TslMethod.builder();
        builder.id(params.getId());
        builder.name(params.getName());
        builder.description(params.getDescription());
        builder.asynchronous(params.getAsynchronous());
        builder.createTime(new Date());
        builder.updateTime(new Date());
        return builder.build();
    }

    @Transactional(rollbackFor = Exception.class)
    public String removeTslMethod(String id) {
        tslMethodService.getOptById(id).orElseThrow(() -> new BizException("要删除的物模型方法不存在"));
        boolean rmMethod = tslMethodService.removeById(id);
        if (!rmMethod) {
            throw new BizException("删除物模型方法失败，请稍后重试");
        }
        List<TslMethodParams> tslMethodParamsList = tslMethodParamsService.listByMethodId(id);
        // 虽然直接根据 methodId 删除很高效 但是暂时想不出来什么方法能够连带删除枚举
        for (TslMethodParams tslMethodParams : tslMethodParamsList) {
            if (DataTypeConstant.ENUM.getDataType().equals(tslMethodParams.getDataType())) {
                // 删除方法参数配置的枚举
                Boolean rmEnumValue = tslEnumValueService
                        .removeByMasterIdAndSource(tslMethodParams.getId(), EnumValueSourceConstant.METHOD_PARAMS.getSource());
                if (!rmEnumValue) {
                    throw new BizException("删除物模型方法失败，请稍后重试");
                }
            }
            boolean rmTslMethodParams = tslMethodParamsService.removeById(tslMethodParams.getId());
            if (!rmTslMethodParams) {
                throw new BizException("删除物模型方法失败，请稍后重试");
            }
        }
        return "成功";
    }
}
