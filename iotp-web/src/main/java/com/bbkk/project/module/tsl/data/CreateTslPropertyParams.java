package com.bbkk.project.module.tsl.data;

import com.bbkk.project.utils.ValidatedGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 创建物模型 属性 接口入参
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-03 14:01
 **/
@Data
public class CreateTslPropertyParams {

    /**
     * 唯一标识
     */
    @NotEmpty(message = "唯一标识不能为空")
    private String id;

    /**
     * 名称
     */
    @NotEmpty(message = "名称不能为空")
    private String name;

    /**
     * 描述
     */
    @NotEmpty(message = "描述不能为空")
    private String description;

    /**
     * 数据类型（int:整数 float:浮点数 enum:枚举）
     */
    @NotEmpty(message = "数据类型不能为空")
    private String dataType;

    /**
     * 最大值，在dataType为数值型时有效
     */
    @NotEmpty(message = "最大值不能为空", groups = {ValidatedGroup.TslOtherDataTypeGroup.class})
    private String maxValue;

    /**
     * 最小值，在dataType为数值型时有效
     */
    @NotEmpty(message = "最小值不能为空", groups = {ValidatedGroup.TslOtherDataTypeGroup.class})
    private String minValue;

    /**
     * 步长
     */
    @NotNull(message = "步长不能为空", groups = {ValidatedGroup.TslOtherDataTypeGroup.class})
    private String stepSize;

    /**
     * 单位
     */
    @NotEmpty(message = "单位不能为空", groups = {ValidatedGroup.TslOtherDataTypeGroup.class})
    private String unit;

    /**
     * 是否是只读属性
     */
    @NotNull(message = "是否是只读属性不能为空")
    private Boolean onlyRead;

    /**
     * 当 dataType 为 enum 时的枚举值定义
     */
    @Valid
    @NotEmpty(message = "数据类型为枚举时，枚举值不能为空", groups = {ValidatedGroup.TslEnumDataTypeGroup.class})
    private List<TslEnumValueParams> enumValueParamsList;

}
