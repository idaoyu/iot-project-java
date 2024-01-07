package com.bbkk.project.module.device.controller;

import com.bbkk.project.module.device.entity.DeviceShadow;
import com.bbkk.project.module.device.service.DeviceShadowBusinessService;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 设备影子接口
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-16 11:45
 **/
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deviceShadow")
public class DeviceShadowController {

    private final DeviceShadowBusinessService deviceShadowBusinessService;

    /**
     * 获取指定设备的设备影子
     *
     * @param deviceId 设备id
     * @return 设备影子数据
     */
    @GetMapping
    public DeviceShadow getShadow(@NotEmpty(message = "设备id不能为空") String deviceId) {
        return deviceShadowBusinessService.getShadow(deviceId);
    }


}
