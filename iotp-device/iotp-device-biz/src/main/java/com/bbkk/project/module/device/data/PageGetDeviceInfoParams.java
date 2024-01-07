package com.bbkk.project.module.device.data;

import com.bbkk.project.data.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询设备信息接口入参
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-14 20:54
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class PageGetDeviceInfoParams extends PageParams {

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 设备名字
     */
    private String name;

}
