package com.bbkk.project.data;

import lombok.Data;

import java.util.List;

/**
 * 属性上报数据 dto
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-25 18:12
 **/
@Data
public class PropertyReportPayloadDTO {

    /**
     * 物模型属性 键值对集合
     */
    private List<PropertyDTO> property;

}
