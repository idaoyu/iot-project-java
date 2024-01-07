package com.bbkk.project.module.tsl.data;

import com.bbkk.project.data.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询物模型事件接口入参
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-12 18:16
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class PageGetTslEventParams extends PageParams {

    /**
     * 物模型事件 id
     */
    private String id;

}
