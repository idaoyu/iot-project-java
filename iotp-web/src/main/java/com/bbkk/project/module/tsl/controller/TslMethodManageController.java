package com.bbkk.project.module.tsl.controller;

import com.bbkk.project.module.tsl.constant.PropertyDataTypeConstant;
import com.bbkk.project.module.tsl.data.CreateTslMethodParams;
import com.bbkk.project.module.tsl.data.OperateTslMethodParamsDTO;
import com.bbkk.project.module.tsl.service.TslMethodManageService;
import com.bbkk.project.utils.ValidatedGroup;
import com.bbkk.project.utils.ValidatedUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 物模型方法管理接口
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-05 21:16
 **/
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manage/tslMethod")
public class TslMethodManageController {

    private final TslMethodManageService tslMethodManageService;

    @PostMapping
    public String createTslMethod(@RequestBody @Validated CreateTslMethodParams params) {
        for (OperateTslMethodParamsDTO inputParam : params.getInputParams()) {
            if (inputParam.getDataType().equals(PropertyDataTypeConstant.ENUM.getDataType())) {
                ValidatedUtil.validateEntity(inputParam, ValidatedGroup.TslEnumDataTypeGroup.class);
            } else {
                ValidatedUtil.validateEntity(inputParam, ValidatedGroup.TslOtherDataTypeGroup.class);
            }
        }
        for (OperateTslMethodParamsDTO outputParams : params.getOutputParams()) {
            if (outputParams.getDataType().equals(PropertyDataTypeConstant.ENUM.getDataType())) {
                ValidatedUtil.validateEntity(outputParams, ValidatedGroup.TslEnumDataTypeGroup.class);
            } else {
                ValidatedUtil.validateEntity(outputParams, ValidatedGroup.TslOtherDataTypeGroup.class);
            }
        }
        return tslMethodManageService.createTslMethod(params);
    }

}
