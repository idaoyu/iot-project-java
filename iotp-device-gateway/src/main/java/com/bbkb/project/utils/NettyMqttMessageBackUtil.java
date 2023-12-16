package com.bbkb.project.utils;

import com.google.common.collect.Lists;
import io.netty.channel.Channel;
import io.netty.handler.codec.mqtt.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * netty mqtt 响应工具类
 * 此工具类参考 https://blog.csdn.net/myyhtw/article/details/114041042
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-16 14:14
 **/
@Slf4j
public class NettyMqttMessageBackUtil {

    /**
     * 确认连接请求
     *
     * @param channel     通道
     * @param mqttMessage mqtt 消息
     */
    public static void connack(Channel channel, MqttMessage mqttMessage) {
        MqttConnectMessage mqttConnectMessage = (MqttConnectMessage) mqttMessage;
        MqttFixedHeader mqttFixedHeader = mqttConnectMessage.fixedHeader();
        MqttConnectVariableHeader mqttConnectVariableHeader = mqttConnectMessage.variableHeader();

        // 构建返回报文，可变报头
        MqttConnAckVariableHeader mqttConnAckVariableHeaderBack = new MqttConnAckVariableHeader(
                MqttConnectReturnCode.CONNECTION_ACCEPTED, mqttConnectVariableHeader.isCleanSession());
        // 构建返回报文，固定报头
        MqttFixedHeader mqttFixedHeaderBack = new MqttFixedHeader(
                MqttMessageType.CONNACK, mqttFixedHeader.isDup(), MqttQoS.AT_MOST_ONCE, mqttFixedHeader.isRetain(), 0x02);
        // 构建 CONNACK 消息体
        MqttConnAckMessage connAck = new MqttConnAckMessage(mqttFixedHeaderBack, mqttConnAckVariableHeaderBack);
        channel.writeAndFlush(connAck);
    }

    /**
     * 根据qos发布确认
     *
     * @param channel     Channel
     * @param mqttMessage mqtt 消息
     */
    public static void puback(Channel channel, MqttMessage mqttMessage) {
        MqttPublishMessage mqttPublishMessage = (MqttPublishMessage) mqttMessage;
        MqttFixedHeader mqttFixedHeaderInfo = mqttPublishMessage.fixedHeader();
        MqttQoS qos = mqttFixedHeaderInfo.qosLevel();
        byte[] headBytes = new byte[mqttPublishMessage.payload().readableBytes()];
        mqttPublishMessage.payload().readBytes(headBytes);
        String data = new String(headBytes);
        log.info("客户端发布消息 -> 消息内容\n{}", data);

        switch (qos) {
            // 至多一次
            case AT_MOST_ONCE:
                subscribePush(mqttMessage);
                break;
            // 至少一次
            case AT_LEAST_ONCE:
                // 构建返回报文， 可变报头
                MqttMessageIdVariableHeader mqttMessageIdVariableHeaderBack = MqttMessageIdVariableHeader.from(
                        mqttPublishMessage.variableHeader().packetId());
                // 构建返回报文， 固定报头
                MqttFixedHeader mqttFixedHeaderBack = new MqttFixedHeader(
                        MqttMessageType.PUBACK, mqttFixedHeaderInfo.isDup(), MqttQoS.AT_MOST_ONCE, mqttFixedHeaderInfo.isRetain(), 0x02);
                // 构建 PUBACK 消息体
                MqttPubAckMessage pubAck = new MqttPubAckMessage(mqttFixedHeaderBack, mqttMessageIdVariableHeaderBack);
                channel.writeAndFlush(pubAck);
                subscribePush(mqttMessage);
                break;
            // 刚好一次
            case EXACTLY_ONCE:
                // 构建返回报文， 固定报头
                MqttFixedHeader mqttFixedHeaderBack2 = new MqttFixedHeader(
                        MqttMessageType.PUBREC, false, MqttQoS.AT_LEAST_ONCE, false, 0x02);
                // 构建返回报文， 可变报头
                MqttMessageIdVariableHeader mqttMessageIdVariableHeaderBack2 = MqttMessageIdVariableHeader.from(
                        mqttPublishMessage.variableHeader().packetId());
                MqttMessage mqttMessageBack = new MqttMessage(mqttFixedHeaderBack2, mqttMessageIdVariableHeaderBack2);
                channel.writeAndFlush(mqttMessageBack);
                subscribePush(mqttMessage);
                break;
            default:
                break;
        }
    }

    /**
     * 发布完成 qos2
     *
     * @param channel     Channel
     * @param mqttMessage mqtt 消息
     */
    public static void pubcomp(Channel channel, MqttMessage mqttMessage) {
        MqttMessageIdVariableHeader messageIdVariableHeader = (MqttMessageIdVariableHeader) mqttMessage.variableHeader();
        // 构建返回报文， 固定报头
        MqttFixedHeader mqttFixedHeaderBack = new MqttFixedHeader(
                MqttMessageType.PUBCOMP, false, MqttQoS.AT_MOST_ONCE, false, 0x02);
        // 构建返回报文， 可变报头
        MqttMessageIdVariableHeader mqttMessageIdVariableHeaderBack = MqttMessageIdVariableHeader.from(messageIdVariableHeader.messageId());
        MqttMessage mqttMessageBack = new MqttMessage(mqttFixedHeaderBack, mqttMessageIdVariableHeaderBack);
        channel.writeAndFlush(mqttMessageBack);
    }

    /**
     * 订阅确认
     *
     * @param channel     Channel
     * @param mqttMessage mqtt 消息
     */
    public static void suback(Channel channel, MqttMessage mqttMessage) {
        MqttSubscribeMessage mqttSubscribeMessage = (MqttSubscribeMessage) mqttMessage;
        MqttMessageIdVariableHeader messageIdVariableHeader = mqttSubscribeMessage.variableHeader();
        // 构建返回报文， 可变报头
        MqttMessageIdVariableHeader variableHeaderBack = MqttMessageIdVariableHeader.from(messageIdVariableHeader.messageId());
        Set<String> topics = mqttSubscribeMessage.payload()
                .topicSubscriptions()
                .stream().map(MqttTopicSubscription::topicName).collect(Collectors.toSet());
        List<Integer> grantedQosLevelList = Lists.newArrayList();
        for (MqttTopicSubscription topicSubscription : mqttSubscribeMessage.payload().topicSubscriptions()) {
            grantedQosLevelList.add(topicSubscription.qualityOfService().value());
        }
        // 构建返回报文 有效负载
        MqttSubAckPayload payloadBack = new MqttSubAckPayload(grantedQosLevelList);
        // 构建返回报文 固定报头
        MqttFixedHeader mqttFixedHeaderBack = new MqttFixedHeader(
                MqttMessageType.SUBACK, false, MqttQoS.AT_MOST_ONCE, false, 2 + topics.size());
        // 构建返回报文 订阅确认
        MqttSubAckMessage subAck = new MqttSubAckMessage(mqttFixedHeaderBack, variableHeaderBack, payloadBack);
        channel.writeAndFlush(subAck);
    }

    /**
     * 取消订阅确认
     *
     * @param channel     Channel
     * @param mqttMessage mqtt 消息
     */
    public static void unsuback(Channel channel, MqttMessage mqttMessage) {
        MqttMessageIdVariableHeader messageIdVariableHeader = (MqttMessageIdVariableHeader) mqttMessage.variableHeader();
        // 构建返回报文 可变报头
        MqttMessageIdVariableHeader variableHeaderBack = MqttMessageIdVariableHeader.from(messageIdVariableHeader.messageId());
        // 构建返回报文 固定报头
        MqttFixedHeader mqttFixedHeaderBack = new MqttFixedHeader(
                MqttMessageType.UNSUBACK, false, MqttQoS.AT_MOST_ONCE, false, 2);
        // 构建返回报文 取消订阅确认
        MqttUnsubAckMessage unSubAck = new MqttUnsubAckMessage(mqttFixedHeaderBack, variableHeaderBack);
        channel.writeAndFlush(unSubAck);
    }

    /**
     * 心跳响应
     *
     * @param channel     Channel
     * @param mqttMessage mqtt 消息
     */
    public static void pingresp(Channel channel, MqttMessage mqttMessage) {
        // 心跳响应报文 11010000 00000000 固定报文
        MqttFixedHeader fixedHeader = new MqttFixedHeader(
                MqttMessageType.PINGRESP, false, MqttQoS.AT_MOST_ONCE, false, 0);
        MqttMessage mqttMessageBack = new MqttMessage(fixedHeader);
        channel.writeAndFlush(mqttMessageBack);
    }

    public static void subscribePush(MqttMessage message) {
        MqttPublishMessage mqttPublishMessage = (MqttPublishMessage) message;
        MqttPublishVariableHeader variableHeader = (MqttPublishVariableHeader) message.variableHeader();
        String topicName = variableHeader.topicName();
        int packetId = variableHeader.packetId();
        //固定消息头 注意此处的消息类型PUBLISH mqtt协议
        MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(
                MqttMessageType.PUBLISH, false, MqttQoS.AT_LEAST_ONCE, false, 0);
        //可变消息头
        MqttPublishVariableHeader mqttPublishVariableHeader = new MqttPublishVariableHeader(topicName, packetId);
        //推送消息体
        MqttPublishMessage mqttPublishMessageResult = new MqttPublishMessage(
                mqttFixedHeader, mqttPublishVariableHeader, mqttPublishMessage.content());
        if (ChannelHolder.CHANNEL_CACHE.containsKey(topicName)) {
            List<Channel> channelList = ChannelHolder.CHANNEL_CACHE.get(topicName);
            for (Channel channel : channelList) {
                log.info("向订阅 {} topic 的设备推送消息 channel id：{}", topicName, channel.id().asLongText());
                //writeAndFlush会将ByteBuf的引用释放refCnt会减去1,使用retain加1
                mqttPublishMessageResult.retain();
                channel.writeAndFlush(mqttPublishMessageResult);
            }
            mqttPublishMessageResult.release();
        }
    }
}
