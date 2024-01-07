package com.bbkk.project.module.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bbkk.project.module.device.entity.DeviceEvidencePool;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-14 19:59
 **/
public interface IDeviceEvidencePoolService extends IService<DeviceEvidencePool> {

    /**
     * 生成设备认证密钥并绑定
     *
     * @param productId 产品id
     * @param deviceId  设备id
     */
    void generateAuthKeyAndBind(Long productId, String deviceId);

    /**
     * 根据设备id 删除对应的认证凭据
     *
     * @param deviceId 设备id
     * @return 成功返回 true
     */
    boolean removeByDeviceId(String deviceId);

}
