package com.bbkb.project.handler;

import com.bbkb.project.utils.ChannelHolder;
import com.bbkb.project.utils.NettyMqttMessageBackUtil;
import com.google.common.collect.Lists;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.mqtt.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

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
        // 当客户端连接到服务端时
        if (mqttFixedHeader.messageType().equals(MqttMessageType.CONNECT)) {
            NettyMqttMessageBackUtil.connack(channel, mqttMessage);
        }
        switch (mqttFixedHeader.messageType()) {
            case SUBSCRIBE:
                // 客户端订阅主题 向客户端响应 订阅确认报文 SUBACK
                // 客户端向服务端发送SUBSCRIBE报文用于创建一个或多个订阅。每个订阅注册客户端关心的一个或多个主题。
                // 为了将应用消息转发给与那些订阅匹配的主题，服务端发送PUBLISH报文给客户端。
                // SUBSCRIBE报文也（为每个订阅）指定了最大的QoS等级，服务端根据这个发送应用消息给客户端。
                log.info("客户端订阅主题 {}", mqttMessage);
                // 获取当前被订阅的 topic
                MqttSubscribeMessage mqttSubscribeMessage = (MqttSubscribeMessage) mqttMessage;
                List<String> topicList = mqttSubscribeMessage.payload().topicSubscriptions()
                        .stream().map(MqttTopicSubscription::topicName).distinct().toList();
                Map<String, List<Channel>> channelCache = ChannelHolder.CHANNEL_CACHE;
                for (String topic : topicList) {
                    if (channelCache.containsKey(topic)) {
                        channelCache.get(topic).add(channel);
                    } else {
                        channelCache.put(topic, Lists.newArrayList(channel));
                    }
                }
                NettyMqttMessageBackUtil.suback(channel, mqttMessage);
                break;
            case UNSUBSCRIBE:
                // 客户端取消订阅 向客户端响应 确认取消订阅报文 UNSUBACK
                log.info("客户端取消订阅 {}", mqttMessage);
                NettyMqttMessageBackUtil.unsuback(channel, mqttMessage);
                break;
            case PINGREQ:
                // 心跳请求 向客户端响应 PINGREQ 报文 让客户端知道 服务端知道客户端还活着
                log.info("客户端心跳请求 {}", mqttMessage);
                NettyMqttMessageBackUtil.pingresp(channel, mqttMessage);
                break;
            case DISCONNECT:
                // 客户端主动断开连接
                log.info("客户端主动断开连接 {}", mqttMessage);
                break;

            // 发布消息部分
            case PUBLISH:
                // 客户端发布消息 根据 qos 响应对应报文
                log.info("客户端发布消息 {}", mqttMessage);
                NettyMqttMessageBackUtil.puback(channel, mqttMessage);
                break;
            case PUBREL:
                // 客户端发布释放报文 是 qos 2 时协议交换的第三个报文
                NettyMqttMessageBackUtil.pubcomp(channel, mqttMessage);
                log.info("客户端发布消息 -> 发布释放 {}", mqttMessage);
                break;
            default:
                break;
        }
    }
}
