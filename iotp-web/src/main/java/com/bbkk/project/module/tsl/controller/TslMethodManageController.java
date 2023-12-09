package com.bbkk.project.module.tsl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bbkk.project.module.tsl.constant.DataTypeConstant;
import com.bbkk.project.module.tsl.data.OperateTslMethodParams;
import com.bbkk.project.module.tsl.data.OperateTslMethodParamsDTO;
import com.bbkk.project.module.tsl.data.PageGetTslMethodParams;
import com.bbkk.project.module.tsl.data.PageGetTslMethodVO;
import com.bbkk.project.module.tsl.service.TslMethodManageService;
import com.bbkk.project.utils.ValidatedGroup;
import com.bbkk.project.utils.ValidatedUtil;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public String createTslMethod(@RequestBody @Validated OperateTslMethodParams params) {
        for (OperateTslMethodParamsDTO dto : params.getParamsList()) {
            if (!("input".equals(dto.getType()) || "output".equals(dto.getType()))) {
                throw new ConstraintViolationException("参数类型不合法，input：输入参数 output：输出参数", null);
            }
            if (dto.getDataType().equals(DataTypeConstant.ENUM.getDataType())) {
                ValidatedUtil.validateEntity(dto, ValidatedGroup.TslEnumDataTypeGroup.class);
            } else {
                ValidatedUtil.validateEntity(dto, ValidatedGroup.TslOtherDataTypeGroup.class);
            }
        }
        return tslMethodManageService.createTslMethod(params);
    }

    @DeleteMapping
    public String removeTslMethod(@NotEmpty(message = "要删除的id不能为空") String id) {
        return tslMethodManageService.removeTslMethod(id);
    }

    @PutMapping
    public String updateTslMethod(@RequestBody @Validated OperateTslMethodParams params) {
        for (OperateTslMethodParamsDTO dto : params.getParamsList()) {
            if (dto.getDataType().equals(DataTypeConstant.ENUM.getDataType())) {
                ValidatedUtil.validateEntity(dto, ValidatedGroup.TslEnumDataTypeGroup.class);
            } else {
                ValidatedUtil.validateEntity(dto, ValidatedGroup.TslOtherDataTypeGroup.class);
            }
        }
        return tslMethodManageService.updateTslMethod(params);
    }

    @GetMapping
    public IPage<PageGetTslMethodVO> pageGetTslMethod(@Validated PageGetTslMethodParams params) {
        return tslMethodManageService.pageGetTslMethod(params);
    }

}
