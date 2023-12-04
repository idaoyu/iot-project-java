package com.bbkk.project.module.tsl.data;

import com.bbkk.project.utils.ValidatedGroup;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 修改物模型属性枚举类型数据入参
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-04 20:43
 **/
@Data
public class UpdateTslEnumValueParams {

    @NotEmpty(message = "枚举值不能为空", groups = {ValidatedGroup.TslEnumDataTypeGroup.class})
    private String value;

    @NotEmpty(message = "枚举值描述不能为空", groups = {ValidatedGroup.TslEnumDataTypeGroup.class})
    private String description;

}
