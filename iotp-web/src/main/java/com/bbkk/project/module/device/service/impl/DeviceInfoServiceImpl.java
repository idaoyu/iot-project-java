package com.bbkk.project.module.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbkk.project.module.device.entity.DeviceInfo;
import com.bbkk.project.module.device.mapper.DeviceInfoMapper;
import com.bbkk.project.module.device.service.IDeviceInfoService;
import org.springframework.stereotype.Service;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-14 20:27
 **/
@Service
public class DeviceInfoServiceImpl extends ServiceImpl<DeviceInfoMapper, DeviceInfo> implements IDeviceInfoService {

    @Override
    public boolean existByProductIdAndName(Long productId, String name) {
        LambdaQueryWrapper<DeviceInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(DeviceInfo::getProductId, productId);
        wrapper.eq(DeviceInfo::getName, name);
        return super.exists(wrapper);
    }
}
