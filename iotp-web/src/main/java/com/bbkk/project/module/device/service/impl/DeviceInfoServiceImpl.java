package com.bbkk.project.module.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbkk.project.module.device.data.PageGetDeviceInfoParams;
import com.bbkk.project.module.device.entity.DeviceInfo;
import com.bbkk.project.module.device.mapper.DeviceInfoMapper;
import com.bbkk.project.module.device.service.IDeviceInfoService;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public IPage<DeviceInfo> pageGet(PageGetDeviceInfoParams params) {
        LambdaQueryWrapper<DeviceInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(params.getProductId() != null, DeviceInfo::getProductId, params.getProductId());
        wrapper.eq(StringUtils.isNotBlank(params.getName()), DeviceInfo::getName, params.getName());
        return super.page(Page.of(params.getCurrent(), params.getPageSize()), wrapper);
    }
}
