package com.bbkk.project.module.upms.data;

import com.bbkk.project.module.web.data.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询权限接口
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-02 11:21
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class PageGetPermissionParams extends PageParams {

    /**
     * 权限名字
     */
    private String name;

}
