package com.bbkk.project.service;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson2.JSONObject;
import com.bbkk.project.data.EmqxWebhookMqttParams;
import com.bbkk.project.data.common.BasePublishMessageDTO;
import com.bbkk.project.data.common.EmqxWebhookResponse;
import com.bbkk.project.data.v1.V1PublishMessageDTO;
import com.influxdb.client.InfluxDBClient;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.bbkk.project.constant.PublishMessageVersionConstant.V1;

/**
 * EMQX Webhook 处理接口 业务逻辑
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-21 18:30
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class EmqxWebHookHandlerService {

    private final InfluxDBClient influxDbClient;

    public EmqxWebhookResponse publishMessageHandler(EmqxWebhookMqttParams params, HttpServletResponse response) {
        BasePublishMessageDTO basePublishMessageDTO = JSONObject.parseObject(params.getPayload(), BasePublishMessageDTO.class);
        if (basePublishMessageDTO.getVersion().equals(V1.getVersion())) {
            V1PublishMessageDTO v1PublishMessageDTO = (V1PublishMessageDTO) JSONObject
                    .parseObject(params.getPayload(), V1.getClazz());
            log.info(JSONObject.toJSONString(v1PublishMessageDTO));
        } else {
            // 如果没有匹配到合适的版本 设置 http 状态码为 400
            response.setStatus(HttpStatus.HTTP_BAD_REQUEST);
            return EmqxWebhookResponse.error("Undefined version or version error.");
        }

        return EmqxWebhookResponse.success();
    }
}
