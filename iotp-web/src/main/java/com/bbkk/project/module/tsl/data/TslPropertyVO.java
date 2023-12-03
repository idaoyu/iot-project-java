package com.bbkk.project.module.tsl.data;

import com.bbkk.project.module.tsl.entity.TslEnumValue;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 物模型属性 vo
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-03 19:07
 **/
@Data
public class TslPropertyVO {

    /**
     * 唯一标识
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 数据类型（int:整数 float:浮点数 enum:枚举）
     */
    private String dataType;

    /**
     * 最大值，在dataType为数值型时有效
     */
    private String maxValue;

    /**
     * 最小值，在dataType为数值型时有效
     */
    private String minValue;

    /**
     * 步长
     */
    private String stepSize;

    /**
     * 单位
     */
    private String unit;

    /**
     * 是否是只读属性
     */
    private Boolean onlyRead;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private List<TslEnumValue> enumValueList;

}
