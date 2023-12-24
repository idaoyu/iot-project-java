package com.bbkk.project.factory.impl;

import com.bbkk.project.constant.PublishActionConstant;
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
        log.info("ReportPublishMessageHandlerImpl do handler");

    }

}
