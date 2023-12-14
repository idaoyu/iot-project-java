package com.bbkk.project.module.device.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bbkk.project.exception.BizException;
import com.bbkk.project.module.device.data.OperationDeviceInfoParams;
import com.bbkk.project.module.device.data.PageGetDeviceInfoParams;
import com.bbkk.project.module.device.entity.DeviceInfo;
import com.bbkk.project.module.product.constant.ProductAuthType;
import com.bbkk.project.module.product.entity.ProductInfo;
import com.bbkk.project.module.product.service.IProductInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 设备信息管理接口 业务逻辑
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-14 20:23
 **/
@Service
@RequiredArgsConstructor
public class DeviceInfoManageService {

    private final IProductInfoService productInfoService;
    private final IDeviceInfoService deviceInfoService;
    private final IDeviceEvidencePoolService deviceEvidencePoolService;

    @Transactional(rollbackFor = Exception.class)
    public String createDeviceInfo(OperationDeviceInfoParams params) {
        ProductInfo productInfo = productInfoService.getOptById(params.getProductId()).orElseThrow(() -> new BizException("指定的产品不存在"));
        // 判断是否有同名的设备
        boolean exist = deviceInfoService.existByProductIdAndName(productInfo.getId(), params.getName());
        if (exist) {
            throw new BizException("设备名重复");
        }
        DeviceInfo.DeviceInfoBuilder builder = DeviceInfo.builder();
        builder.productId(productInfo.getId());
        builder.name(params.getName());
        builder.description(params.getDescription());
        builder.createTime(new Date());
        builder.updateTime(new Date());
        DeviceInfo deviceInfo = builder.build();
        boolean save = deviceInfoService.save(deviceInfo);
        if (!save) {
            throw new BizException("创建设备失败，请稍后重试");
        }
        // 如果产品需要认证 并且 认证方式为一个设备一个密钥
        if (productInfo.getNeedAuth() && ProductAuthType.BIND_DEVICE.getType().equals(productInfo.getAuthType())) {
            deviceEvidencePoolService.generateAuthKeyAndBind(productInfo.getId(), deviceInfo.getId());
        }
        return "成功";
    }

    public String removeDeviceInfo(String id) {
        deviceInfoService.getOptById(id).orElseThrow(() -> new BizException("要删除的设备不存在"));
        boolean rmDeviceInfo = deviceInfoService.removeById(id);
        if (!rmDeviceInfo) {
            throw new BizException("删除设备时出现错误，请稍后重试");
        }
        boolean rmEvidence = deviceEvidencePoolService.removeByDeviceId(id);
        if (!rmEvidence) {
            throw new BizException("删除设备时出现错误，请稍后重试");
        }
        return "成功";
    }

    public String updateDeviceInfo(OperationDeviceInfoParams params) {
        DeviceInfo deviceInfo = deviceInfoService.getOptById(params.getId()).orElseThrow(() -> new BizException("要修改的设备不存在"));
        deviceInfo.setName(params.getName());
        deviceInfo.setDescription(params.getDescription());
        boolean update = deviceInfoService.updateById(deviceInfo);
        if (!update) {
            throw new BizException("修改设备失败，请稍后重试");
        }
        return "成功";
    }

    public IPage<DeviceInfo> pageGet(PageGetDeviceInfoParams params) {
        return deviceInfoService.pageGet(params);
    }
}
