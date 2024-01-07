package com.bbkk.project.module.tsl.data;

import com.bbkk.project.utils.ValidatedGroup;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 物模型枚举类型入参
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-03 14:02
 **/
@Data
public class OperateTslEnumValueParams {

    @NotEmpty(message = "枚举值不能为空", groups = {ValidatedGroup.TslEnumDataTypeGroup.class})
    private String value;

    @NotEmpty(message = "枚举值描述不能为空", groups = {ValidatedGroup.TslEnumDataTypeGroup.class})
    private String description;

}
