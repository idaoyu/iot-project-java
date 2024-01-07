package com.bbkk.project.factory.impl;

import com.alibaba.fastjson2.JSONObject;
import com.bbkk.project.constant.PublishActionConstant;
import com.bbkk.project.data.PropertyReportPayloadDTO;
import com.bbkk.project.data.PublishMessageDTO;
import com.bbkk.project.factory.IPublishMessageHandlerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 客户端上报属性处理器
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-24 12:42
 **/
@Slf4j
@RequiredArgsConstructor
@Service(PublishActionConstant.REPORT)
public class ReportPublishMessageHandlerImpl implements IPublishMessageHandlerFactory {

    @Override
    public void handler(PublishMessageDTO publishMessageDTO) {
        PropertyReportPayloadDTO dto;
        try {
            dto = JSONObject.parseObject(publishMessageDTO.getPayload(), PropertyReportPayloadDTO.class);
        } catch (Exception ex) {
            log.warn("上报数据解析失败 设备id: {} 产品id: {} 原始数据内容:{}"
                    , publishMessageDTO.getDeviceId()
                    , publishMessageDTO.getProductId()
                    , publishMessageDTO.getPayload());
            return;
        }
        log.info(JSONObject.toJSONString(dto));

    }

}
