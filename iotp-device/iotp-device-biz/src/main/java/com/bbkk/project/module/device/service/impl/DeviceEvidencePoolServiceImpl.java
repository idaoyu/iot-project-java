package com.bbkk.project.module.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbkk.project.exception.BizException;
import com.bbkk.project.module.device.entity.DeviceEvidencePool;
import com.bbkk.project.module.device.mapper.DeviceEvidencePoolMapper;
import com.bbkk.project.module.device.service.IDeviceEvidencePoolService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-14 19:59
 **/
@Service
public class DeviceEvidencePoolServiceImpl extends ServiceImpl<DeviceEvidencePoolMapper, DeviceEvidencePool> implements IDeviceEvidencePoolService {

    @Override
    public void generateAuthKeyAndBind(Long productId, String deviceId) {
        DeviceEvidencePool.DeviceEvidencePoolBuilder builder = DeviceEvidencePool.builder();
        builder.productId(productId);
        builder.deviceId(deviceId);
        builder.secretKey(null);
        builder.createTime(new Date());
        builder.updateTime(new Date());
        boolean save = super.save(builder.build());
        if (!save) {
            throw new BizException("生成认证密钥失败，请稍后重试");
        }
    }

    @Override
    public boolean removeByDeviceId(String deviceId) {
        LambdaQueryWrapper<DeviceEvidencePool> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(DeviceEvidencePool::getDeviceId, deviceId);
        if (!super.exists(wrapper)) {
            return true;
        }
        return super.remove(wrapper);
    }
}
