package com.bbkb.project.service;

import com.bbkb.project.handler.DemoNettyMqttChannelInboundHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-16 13:01
 **/
@Slf4j
public class NettyMqttService {

    /**
     * 负责接收客户端连接
     */
    private NioEventLoopGroup parentGroup;

    /**
     * 负责io操作
     */
    private NioEventLoopGroup childGroup;

    public void startUp(Integer port) {
        parentGroup = new NioEventLoopGroup(1);
        childGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(parentGroup, childGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);

        // 更多解释参阅 https://juejin.cn/post/6982470261811445791
        serverBootstrap
                // 为 true 时表示地址复用 具体情况请参阅文档，这块写不下
                .option(ChannelOption.SO_REUSEADDR, true)
                // 服务端接收连接的队列长度，如果队列已满，连接会被拒绝
                .option(ChannelOption.SO_BACKLOG, 1024)
                // 重用缓冲区 Netty 4.1 为 PooledByteBufAllocator.DEFAULT
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                // TCP 接受缓存区的容量上限
                .option(ChannelOption.SO_RCVBUF, 10285760);
        serverBootstrap
                // 用于开启或关闭 Nagle 算法，该算法用于将小的碎片数据连接成更大的报文，如果需要发送的报文较小则设置为 true 意为关闭 Nagle 算法
                .childOption(ChannelOption.TCP_NODELAY, true)
                // 是否开启 tcp 心跳机制 为 true 时保持心跳
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                // 重用缓冲区 Netty 4.1 为 PooledByteBufAllocator.DEFAULT
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        serverBootstrap.childHandler(
                // 设置出站解码器和入站编码器
                new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        // 设置 读写空闲超时时间
                        pipeline.addLast(new IdleStateHandler(600, 600, 1200));
                        // 设置 mqtt 编码、解码器
                        pipeline.addLast("encoder", MqttEncoder.INSTANCE);
                        pipeline.addLast("decoder", new MqttDecoder());
                        pipeline.addLast(new DemoNettyMqttChannelInboundHandler());
                    }
                }
        );
        try {
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            if (channelFuture.isSuccess()) {
                log.info("netty mqtt start success! bind port: {}", port);
            } else {
                log.error("netty mqtt start error");
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void shutdown() throws InterruptedException {
        if (parentGroup != null && childGroup != null) {
            parentGroup.shutdownGracefully().sync();
            childGroup.shutdownGracefully().sync();
            log.info("netty mqtt stop success!");
        }
    }

}
