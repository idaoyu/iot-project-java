package com.bbkk.project.factory;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * IPublishMessageHandlerFactory 工厂上下文数据
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-24 12:53
 **/
@Component
public class PublishMessageHandlerFactoryContext {

    @Autowired
    private Map<String, IPublishMessageHandlerFactory> map = Maps.newHashMap();

    public IPublishMessageHandlerFactory getImpl(String name) {
        if (!map.containsKey(name)) {
            throw new NullPointerException("impl not fount");
        }
        return map.get(name);
    }

}
