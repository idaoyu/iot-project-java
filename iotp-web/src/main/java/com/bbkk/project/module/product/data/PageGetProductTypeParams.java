package com.bbkk.project.module.product.data;

import com.bbkk.project.module.web.data.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询产品类目接口入参
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-12 18:49
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class PageGetProductTypeParams extends PageParams {

    /**
     * 类目名字
     */
    private String name;

}
