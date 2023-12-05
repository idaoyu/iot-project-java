package com.bbkk.project.module.tsl.service;

import com.alibaba.fastjson2.JSONObject;
import com.bbkk.project.module.tsl.data.CreateTslMethodParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 物模型方法管理接口 业务逻辑
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-05 21:20
 **/
@Service
@RequiredArgsConstructor
public class TslMethodManageService {

    public String createTslMethod(CreateTslMethodParams params) {
        System.out.println(JSONObject.toJSONString(params));
        return "成功";
    }
}
