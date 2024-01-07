package com.bbkk.project.module.upms.data;

import com.bbkk.project.data.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询角色接口入参对象
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-02 10:00
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class PageGetRoleParams extends PageParams {

    /**
     * 角色名字
     */
    private String name;

}
