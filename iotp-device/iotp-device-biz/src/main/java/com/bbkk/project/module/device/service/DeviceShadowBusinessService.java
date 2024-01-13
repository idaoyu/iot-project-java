package com.bbkk.project.module.device.service;

import com.bbkk.project.constant.RedisCacheKeyConstant;
import com.bbkk.project.exception.BizException;
import com.bbkk.project.module.device.entity.DeviceShadow;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

/**
 * 设备影子接口 业务逻辑
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-16 11:48
 **/
@Service
@RequiredArgsConstructor
public class DeviceShadowBusinessService {

    private final IDeviceShadowService deviceShadowService;
    private final RedissonClient redissonClient;

    public DeviceShadow getShadow(String deviceId) {
        // 通过设备id 从 redis 中尝试获取影子数据
        RBucket<DeviceShadow> bucket = redissonClient.getBucket(RedisCacheKeyConstant.IOT_DEVICE_SHADOW_CACHE.getKey() + deviceId);
        DeviceShadow deviceShadow = bucket.get();
        // 如果 redis 中有数据 直接返回
        if (deviceShadow != null) {
            return deviceShadow;
        }
        // 查询数据库
        deviceShadow = deviceShadowService.getOptById(deviceId).orElseThrow(() -> new BizException("未查询到设备影子数据"));
        // 设置 redis 缓存
        bucket.set(deviceShadow, RedisCacheKeyConstant.IOT_DEVICE_SHADOW_CACHE.getDuration());
        return deviceShadow;
    }
}
