package com.bbkk.project.module.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bbkk.project.module.upms.entity.SystemUserRole;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-29 19:00
 **/
public interface ISystemUserRoleService extends IService<SystemUserRole> {

    /**
     * 根据 roleId 删除记录
     *
     * @param roleId 角色id
     * @return 返回 true 成功
     */
    Boolean removeByRoleId(Long roleId);

    /**
     * 根据 userId 删除记录
     *
     * @param id 用户id
     * @return 返回 true 成功
     */
    Boolean removeByUserId(Long id);
}
