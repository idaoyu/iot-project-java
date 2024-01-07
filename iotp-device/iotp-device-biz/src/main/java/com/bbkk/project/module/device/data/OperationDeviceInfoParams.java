package com.bbkk.project.module.device.data;

import com.bbkk.project.utils.ValidatedGroup;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 操作设备信息接口入参
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-14 20:20
 **/
@Data
public class OperationDeviceInfoParams {

    /**
     * 设备id
     */
    @NotEmpty(message = "id 不能为空", groups = {ValidatedGroup.UpdateGroup.class})
    private String id;

    /**
     * 产品id
     */
    @NotNull(message = "绑定的产品id不能为空", groups = {ValidatedGroup.CreateGroup.class})
    private Long productId;

    /**
     * 设备名字
     */
    @NotEmpty(message = "设备名字不能为空")
    private String name;

    /**
     * 设备描述
     */
    @NotEmpty(message = "设备描述不能为空")
    private String description;

}
