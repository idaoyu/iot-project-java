package com.bbkb.project.utils;

import com.google.common.collect.Maps;
import io.netty.channel.Channel;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-16 19:35
 **/
@Data
public class ChannelHolder {

    /**
     * 主要用来存放 topic 对应的 Channel
     */
    public static final Map<String, List<Channel>> CHANNEL_CACHE = Maps.newHashMap();

}
