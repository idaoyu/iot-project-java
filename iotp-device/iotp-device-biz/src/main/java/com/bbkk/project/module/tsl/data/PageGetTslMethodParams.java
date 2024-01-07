package com.bbkk.project.module.tsl.data;

import com.bbkk.project.data.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询物模型方法
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-09 17:50
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class PageGetTslMethodParams extends PageParams {

    /**
     * 物模型方法id
     */
    private String id;

    /**
     * 物模型方法名字（支持模糊查询）
     */
    private String name;

}
