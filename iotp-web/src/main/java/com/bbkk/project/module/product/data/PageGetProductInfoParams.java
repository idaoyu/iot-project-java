package com.bbkk.project.module.product.data;

import com.bbkk.project.module.web.data.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询产品接口入参
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-12 19:25
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class PageGetProductInfoParams extends PageParams {

    /**
     * 产品名字，支持模糊查询
     */
    private String name;

}
