package com.bbkk.project.module.device.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bbkk.project.module.device.data.PageGetDeviceInfoParams;
import com.bbkk.project.module.device.entity.DeviceInfo;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-14 20:27
 **/
public interface IDeviceInfoService extends IService<DeviceInfo> {

    /**
     * 判断是否存在指定的设备
     *
     * @param productId 产品id
     * @param name      设备名字
     * @return 存在返回 true
     */
    boolean existByProductIdAndName(Long productId, String name);

    /**
     * 分页查询设备信息
     *
     * @param params PageGetDeviceInfoParams params
     * @return IPage<PageGetDeviceInfoParams params>
     */
    IPage<DeviceInfo> pageGet(PageGetDeviceInfoParams params);

}

