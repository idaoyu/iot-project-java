package com.bbkk.project.controller;

import com.bbkk.project.data.EmqxMqttPublishParams;
import com.bbkk.project.data.EmqxSessionSubscribedParams;
import com.bbkk.project.data.common.EmqxWebhookResponse;
import com.bbkk.project.service.EmqxWebHookHandlerService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * EMQX Webhook 处理接口
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-17 18:48
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/emqx")
public class EmqxWebHookHandlerController {

    private final EmqxWebHookHandlerService emqxWebHookHandlerService;

    /**
     * 消息发布
     *
     * @param params   EmqxWebhookMqttParams
     * @param response HttpServletResponse
     * @return EmqxWebhookResponse
     */
    @PostMapping("/publishMessage")
    public EmqxWebhookResponse publishMessageHandler(
            @RequestBody EmqxMqttPublishParams params, HttpServletResponse response) {
        return emqxWebHookHandlerService.publishMessageHandler(params, response);
    }

    /**
     * 客户端订阅成功
     *
     * @param params   EmqxSessionSubscribedParams
     * @param response HttpServletResponse
     * @return EmqxWebhookResponse
     */
    @PostMapping("/sessionSubscribed")
    public EmqxWebhookResponse sessionSubscribedHandler(
            @RequestBody EmqxSessionSubscribedParams params, HttpServletResponse response) {
        return emqxWebHookHandlerService.sessionSubscribedHandler(params, response);
    }

}
