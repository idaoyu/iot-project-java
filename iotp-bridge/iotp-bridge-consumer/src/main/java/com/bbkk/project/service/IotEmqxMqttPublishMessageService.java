package com.bbkk.project.service;

import com.bbkk.project.data.PublishMessageDTO;
import com.bbkk.project.factory.IPublishMessageHandlerFactory;
import com.bbkk.project.factory.PublishMessageHandlerFactoryContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * IotEmqxMqttPublishMessageConsumer 业务逻辑
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-24 12:51
 **/
@Service
@RequiredArgsConstructor
public class IotEmqxMqttPublishMessageService {

    private final PublishMessageHandlerFactoryContext publishMessageHandlerFactoryContext;

    public void handlerMessage(PublishMessageDTO message) {
        // 通过发布消息的类型 获取对应的工厂实现
        IPublishMessageHandlerFactory impl = publishMessageHandlerFactoryContext.getImpl(message.getType());
        // 去处理数据
        impl.handler(message);
    }
}
