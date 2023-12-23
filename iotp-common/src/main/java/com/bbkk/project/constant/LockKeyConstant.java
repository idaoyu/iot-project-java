package com.bbkk.project.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 项目中锁/分布式锁等 key
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-23 17:13
 **/
@Getter
@AllArgsConstructor
public enum LockKeyConstant {

    /**
     * 当设备订阅主题后，需要完成一些操作
     * 例如向设备推送物模型信息、设备影子等
     * 需要保证在设备上线订阅主题时链路中只处理一次
     * 避免由于网络延迟或其他原因 设备未收到信息重发导致的性能问题
     */
    DEVICE_SUBSCRIPTION_TOPIC_LOCK("device_subscription_topic:"),
    ;

    private final String key;

}
