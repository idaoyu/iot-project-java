package com.bbkk.project.module.upms.data;

import com.bbkk.project.data.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询用户接口入参
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-03 11:49
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class PageGetUserParams extends PageParams {

    /**
     * 用户名
     */
    private String username;

    /**
     * 是否启用
     */
    private Boolean enable;

}
