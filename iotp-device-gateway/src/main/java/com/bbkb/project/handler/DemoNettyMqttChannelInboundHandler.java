package com.bbkb.project.handler;

import com.bbkb.project.utils.NettyMqttMessageBackUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageType;
import lombok.extern.slf4j.Slf4j;

/**
 * netty mqtt 处理器 demo
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-16 13:48
 **/
@Slf4j
@ChannelHandler.Sharable
public class DemoNettyMqttChannelInboundHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当客户端收到消息时，该方法会被调用
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg == null) {
            return;
        }
        MqttMessage mqttMessage = (MqttMessage) msg;
        MqttFixedHeader mqttFixedHeader = mqttMessage.fixedHeader();
        Channel channel = ctx.channel();
        if (mqttFixedHeader.messageType().equals(MqttMessageType.CONNECT)) {
            NettyMqttMessageBackUtil.ackConnect(channel, mqttMessage);
        }
        log.info("info -> {}", mqttMessage);

        super.channelRead(ctx, msg);
    }
}
