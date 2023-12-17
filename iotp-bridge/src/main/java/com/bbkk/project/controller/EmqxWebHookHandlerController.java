package com.bbkk.project.controller;

import com.alibaba.fastjson2.JSONObject;
import com.bbkk.project.data.EmqxWebhookResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * EMQX Webhook 处理接口
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-17 18:48
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/emqxWebhook")
public class EmqxWebHookHandlerController {

    @PostMapping("/publishMessage")
    public EmqxWebhookResponse publishMessageHandler(@RequestBody Map<String, Object> data) {
        log.info("客户端发布消息 消息内容: {}", JSONObject.toJSONString(data));
        return EmqxWebhookResponse.success();
    }

}
