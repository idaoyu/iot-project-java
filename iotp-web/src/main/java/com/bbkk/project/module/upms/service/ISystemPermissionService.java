package com.bbkk.project.module.upms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bbkk.project.module.upms.data.PageGetPermissionParams;
import com.bbkk.project.module.upms.entity.SystemPermission;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-29 18:58
 **/
public interface ISystemPermissionService extends IService<SystemPermission> {

    /**
     * 分页查询权限
     *
     * @param params PageGetPermissionParams
     * @return IPage<SystemPermission>
     */
    IPage<SystemPermission> pageGetPermission(PageGetPermissionParams params);
}
