package com.bbkk.project.module.upms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bbkk.project.module.upms.data.PageGetRoleParams;
import com.bbkk.project.module.upms.entity.SystemRole;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-29 18:58
 **/
public interface ISystemRoleService extends IService<SystemRole> {

    /**
     * 根据参数 分页查询角色
     *
     * @param params PageGetRoleParams
     * @return IPage<SystemRole>
     */
    IPage<SystemRole> pageGetRole(PageGetRoleParams params);
}
