package com.bbkk.project.module.tsl.data;

import com.bbkk.project.utils.ValidatedGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 * 操作物模型方法输入参数/输出参数对象
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-05 21:31
 **/
@Data
public class OperateTslMethodParamsDTO {

    /**
     * 参数唯一标识
     */
    @NotEmpty(message = "参数唯一标识不能为空")
    private String identifier;

    /**
     * 参数名称
     */
    @NotEmpty(message = "参数名称不能为空")
    private String name;

    /**
     * 参数描述
     */
    @NotEmpty(message = "参数描述不能为空")
    private String description;

    /**
     * 数据类型
     */
    @NotEmpty(message = "数据类型不能为空")
    private String dataType;

    /**
     * 最小值
     */
    @NotEmpty(message = "最小值不能为空", groups = {ValidatedGroup.TslOtherDataTypeGroup.class})
    private String minValue;

    /**
     * 最大值
     */
    @NotEmpty(message = "最大值不能为空", groups = {ValidatedGroup.TslOtherDataTypeGroup.class})
    private String maxValue;

    /**
     * 步长
     */
    @NotEmpty(message = "步长不能为空", groups = {ValidatedGroup.TslOtherDataTypeGroup.class})
    private String stepSize;

    /**
     * 单位
     */
    @NotEmpty(message = "单位不能为空", groups = {ValidatedGroup.TslOtherDataTypeGroup.class})
    private String unit;

    @Valid
    @NotEmpty(message = "数据类型为枚举时，枚举值不能为空", groups = {ValidatedGroup.TslEnumDataTypeGroup.class})
    private List<OperateTslEnumValueParams> enumValueParamsList;

    /**
     * 用来区分是输入参数还是输出参数
     * input：输入 output：输出
     */
    @NotEmpty(message = "输入/输出参数类型不能为空")
    private String type;

}
