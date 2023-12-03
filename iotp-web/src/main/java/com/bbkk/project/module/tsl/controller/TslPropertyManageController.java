package com.bbkk.project.module.tsl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bbkk.project.module.tsl.constant.PropertyDataTypeConstant;
import com.bbkk.project.module.tsl.data.CreateTslPropertyParams;
import com.bbkk.project.module.tsl.data.PageGetTslPropertyParams;
import com.bbkk.project.module.tsl.data.TslPropertyVO;
import com.bbkk.project.module.tsl.service.TslPropertyManageService;
import com.bbkk.project.utils.ValidatedGroup;
import com.bbkk.project.utils.ValidatedUtil;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 物模型属性管理接口
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-03 13:57
 **/
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manage/tslProperty")
public class TslPropertyManageController {

    private final TslPropertyManageService tslPropertyManageService;

    @PostMapping
    public String createTslProperty(@RequestBody @Validated CreateTslPropertyParams params) {
        if (PropertyDataTypeConstant.ENUM.getDataType().equals(params.getDataType())) {
            ValidatedUtil.validateEntity(params, ValidatedGroup.TslEnumDataTypeGroup.class);
        } else {
            ValidatedUtil.validateEntity(params, ValidatedGroup.TslOtherDataTypeGroup.class);
        }
        return tslPropertyManageService.createTslProperty(params);
    }

    @DeleteMapping
    public String removeTslProperty(@NotEmpty(message = "物模型属性id不能为空") String id) {
        return tslPropertyManageService.removeTslProperty(id);
    }

    @GetMapping
    public IPage<TslPropertyVO> pageGetTslProperty(@Validated PageGetTslPropertyParams params) {
        return tslPropertyManageService.pageGetTslProperty(params);
    }

}
