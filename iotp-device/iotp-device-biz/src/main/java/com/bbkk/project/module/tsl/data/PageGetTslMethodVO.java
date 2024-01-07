package com.bbkk.project.module.tsl.data;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 分页查询物模型方法 vo
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-09 17:52
 **/
@Data
@Builder
public class PageGetTslMethodVO {

    /**
     * 物模型方法 id
     */
    private String id;

    /**
     * 物模型方法名字
     */
    private String name;

    /**
     * 物模型方法描述
     */
    private String description;

    /**
     * 是否异步
     */
    private Boolean asynchronous;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 方法参数列表
     */
    private List<PageGetTslMethodParamsVO> paramsList;


}
