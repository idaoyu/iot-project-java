package com.bbkk.project.module.tsl.data;

import com.bbkk.project.data.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询物模型属性接口入参
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-03 19:08
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class PageGetTslPropertyParams extends PageParams {

    private String name;

    private String dataType;

}
