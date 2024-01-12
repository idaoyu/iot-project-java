package com.bbkk.project.factory;

import com.bbkk.project.data.PublishMessageDTO;

/**
 * 客户端发布事件处理器工厂接口
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-24 12:39
 **/
public interface IPublishMessageHandlerFactory {

    /**
     * 处理发布数据
     *
     * @param publishMessageDTO PublishMessageDTO
     */
    void handler(PublishMessageDTO publishMessageDTO);

}
