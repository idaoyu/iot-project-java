package com.bbkk.project.module.upms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bbkk.project.module.upms.data.PageGetUserParams;
import com.bbkk.project.module.upms.entity.SystemUser;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-29 18:59
 **/
public interface ISystemUserService extends IService<SystemUser> {

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return SystemUser
     */
    SystemUser findUserByUsername(String username);

    /**
     * 根据参数分页查询用户
     *
     * @param params PageGetUserParams
     * @return IPage<SystemUser>
     */
    IPage<SystemUser> pageGetUser(PageGetUserParams params);
}
