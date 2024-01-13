package com.bbkk.project.factory.impl;

import com.alibaba.fastjson2.JSONObject;
import com.bbkk.project.api.RemoteProductInfoService;
import com.bbkk.project.constant.PublishActionConstant;
import com.bbkk.project.data.GetProductInfoDTO;
import com.bbkk.project.data.PropertyReportPayloadDTO;
import com.bbkk.project.data.PublishMessageDTO;
import com.bbkk.project.data.R;
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

    private final RemoteProductInfoService remoteProductInfoService;

    @Override
    public void handler(PublishMessageDTO publishMessageDTO) {
        PropertyReportPayloadDTO dto;
        try {
            dto = JSONObject.parseObject(publishMessageDTO.getPayload(), PropertyReportPayloadDTO.class);
            log.info("读取上报内容: {}", JSONObject.toJSONString(dto));
        } catch (Exception ex) {
            log.warn("上报数据解析失败 设备id: {} 产品id: {} 原始数据内容:{}"
                    , publishMessageDTO.getDeviceId()
                    , publishMessageDTO.getProductId()
                    , publishMessageDTO.getPayload());
            return;
        }
        R<GetProductInfoDTO> remoteGetProductInfoResult = remoteProductInfoService.getProductInfo(
                publishMessageDTO.getProductId());
        // 如果上游服务产生异常
        if (!remoteGetProductInfoResult.getSuccess()) {
            log.error("处理 mqtt 客户端上报属性时上游服务产生异常，errorCode:{} errorMessage: {}"
                    , remoteGetProductInfoResult.getErrorCode(), remoteGetProductInfoResult.getErrorMessage());
            return;
        }
        GetProductInfoDTO getProductInfoDTO = remoteGetProductInfoResult.getData();
        // 如果产品配置中开启了保存上报数据 则执行保存逻辑
        if (getProductInfoDTO.getNeedSaveProperty()) {
            doSaveReportData(publishMessageDTO, dto, getProductInfoDTO);
        }
    }

    /**
     * 保存上报数据
     *
     * @param publishMessageDTO        原始推送 dto
     * @param propertyReportPayloadDTO 推送内容 dto
     * @param getProductInfoDTO        产品信息 dto
     */
    private void doSaveReportData(PublishMessageDTO publishMessageDTO, PropertyReportPayloadDTO propertyReportPayloadDTO
            , GetProductInfoDTO getProductInfoDTO) {
        // todo 保存数据至 influxdb 但需要采用抽象模式 支持其他时序数据库

    }

}
