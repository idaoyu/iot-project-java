package com.bbkk.project.module.upms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbkk.project.module.upms.data.PageGetUserParams;
import com.bbkk.project.module.upms.entity.SystemUser;
import com.bbkk.project.module.upms.mapper.SystemUserMapper;
import com.bbkk.project.module.upms.service.ISystemUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-29 19:03
 **/
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements ISystemUserService {

    @Override
    public SystemUser findUserByUsername(String username) {
        LambdaQueryWrapper<SystemUser> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SystemUser::getUsername, username);
        return super.getOne(wrapper);
    }

    @Override
    public IPage<SystemUser> pageGetUser(PageGetUserParams params) {
        LambdaQueryWrapper<SystemUser> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(StringUtils.isNotBlank(params.getUsername()), SystemUser::getUsername, params.getUsername());
        wrapper.eq(params.getEnable() != null, SystemUser::getEnable, params.getEnable());
        return super.page(Page.of(params.getCurrent(), params.getPageSize()), wrapper);
    }
}
