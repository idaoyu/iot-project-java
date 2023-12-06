package com.bbkk.project.module.tsl.service;

import com.bbkk.project.exception.BizException;
import com.bbkk.project.module.tsl.constant.DataTypeConstant;
import com.bbkk.project.module.tsl.constant.EnumValueSource;
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
                builder.source(EnumValueSource.METHOD_PARAMS.getSource());
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
}
