package com.bbkk.project.module.tsl.controller;

import com.bbkk.project.module.tsl.data.OperateTslEventParams;
import com.bbkk.project.module.tsl.service.TslEventManageService;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 物模型事件管理接口
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-11 18:41
 **/
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manage/tslEvent")
public class TslEventManageController {

    private final TslEventManageService tslEventManageService;

    @PostMapping
    public String createTslEvent(@RequestBody @Validated OperateTslEventParams params) {
        return tslEventManageService.createTslEvent(params);
    }

    @DeleteMapping
    public String removeTslEvent(@NotEmpty(message = "物模型事件id不能为空") String id) {
        return tslEventManageService.removeTslEvent(id);
    }

    @PutMapping
    public String updateTslEvent(@RequestBody @Validated OperateTslEventParams params) {
        return tslEventManageService.updateTslEvent(params);
    }

}
