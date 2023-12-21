package com.bbkk.project.data.common;

import lombok.Builder;
import lombok.Data;

/**
 * EMQX 通过 webhook 调用服务时接口响应的数据结构
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-17 18:51
 **/
@Data
@Builder
public class EmqxWebhookResponse {

    private String result;

    private String message;

    public static EmqxWebhookResponse success() {
        return EmqxWebhookResponse.builder().result("ok").message("success").build();
    }

    public static EmqxWebhookResponse error(String message) {
        return EmqxWebhookResponse.builder().result("error").message(message).build();
    }

}
