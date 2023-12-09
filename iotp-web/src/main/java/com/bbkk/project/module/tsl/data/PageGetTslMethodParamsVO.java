package com.bbkk.project.module.tsl.data;

import com.bbkk.project.module.tsl.entity.TslEnumValue;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 物模型方法参数
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-09 17:54
 **/
@Data
@Builder
public class PageGetTslMethodParamsVO {

    /**
     * 方法参数id
     */
    private Long id;

    /**
     * 方法参数唯一id
     */
    private String identifier;

    /**
     * 方法参数名字
     */
    private String name;

    /**
     * 方法参数描述
     */
    private String description;

    /**
     * 方法参数类型 输入/输出
     */
    private String type;

    /**
     * 方法参数数据类型
     */
    private String dataType;

    /**
     * 最小值
     */
    private String minValue;

    /**
     * 最大值
     */
    private String maxValue;

    /**
     * 步长
     */
    private String stepSize;

    /**
     * 单位
     */
    private String unit;

    /**
     * 当数据类型为枚举时，枚举值列表
     */
    private List<TslEnumValue> enumValueList;

}
