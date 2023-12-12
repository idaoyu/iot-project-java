package com.bbkk.project.module.tsl.data;

import com.bbkk.project.module.tsl.entity.TslProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 查询物模型事件 vo
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-12 18:17
 **/
@Data
@Builder
public class PageGetTslEventVO {

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
     * 事件类型
     */
    private String type;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 物模型绑定的属性集合
     */
    private List<TslProperty> propertyList;

}
