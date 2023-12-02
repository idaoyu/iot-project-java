package com.bbkk.project.module.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bbkk.project.module.upms.entity.SystemRolePermission;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-29 18:59
 **/
public interface ISystemRolePermissionService extends IService<SystemRolePermission> {

    /**
     * 根据角色id 删除记录
     *
     * @param roleId 角色id
     * @return 成功返回 true
     */
    Boolean removeByRoleId(Long roleId);

}
