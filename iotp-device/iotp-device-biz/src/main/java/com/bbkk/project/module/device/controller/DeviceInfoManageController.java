package com.bbkk.project.module.device.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bbkk.project.module.device.data.OperationDeviceInfoParams;
import com.bbkk.project.module.device.data.PageGetDeviceInfoParams;
import com.bbkk.project.module.device.data.PageGetDeviceInfoVO;
import com.bbkk.project.module.device.service.DeviceInfoManageService;
import com.bbkk.project.utils.ValidatedGroup;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 设备信息管理接口
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-14 20:07
 **/
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manage/deviceInfo")
public class DeviceInfoManageController {

    private final DeviceInfoManageService deviceInfoManageService;

    @PostMapping
    public String createDeviceInfo(@RequestBody @Validated(ValidatedGroup.CreateGroup.class) OperationDeviceInfoParams params) {
        return deviceInfoManageService.createDeviceInfo(params);
    }

    @DeleteMapping
    public String removeDeviceInfo(@NotEmpty(message = "id不能为空") String id) {
        return deviceInfoManageService.removeDeviceInfo(id);
    }

    @PutMapping
    public String updateDeviceInfo(@RequestBody @Validated(ValidatedGroup.UpdateGroup.class) OperationDeviceInfoParams params) {
        return deviceInfoManageService.updateDeviceInfo(params);
    }

    @GetMapping
    public IPage<PageGetDeviceInfoVO> pageGet(@Validated PageGetDeviceInfoParams params) {
        return deviceInfoManageService.pageGet(params);
    }

}
