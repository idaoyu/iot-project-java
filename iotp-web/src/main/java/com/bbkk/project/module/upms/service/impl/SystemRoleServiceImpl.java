package com.bbkk.project.module.upms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbkk.project.module.upms.data.PageGetRoleParams;
import com.bbkk.project.module.upms.entity.SystemRole;
import com.bbkk.project.module.upms.mapper.SystemRoleMapper;
import com.bbkk.project.module.upms.service.ISystemRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-29 19:02
 **/
@Service
public class SystemRoleServiceImpl extends ServiceImpl<SystemRoleMapper, SystemRole> implements ISystemRoleService {

    @Override
    public IPage<SystemRole> pageGetRole(PageGetRoleParams params) {
        LambdaQueryWrapper<SystemRole> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StringUtils.isNotBlank(params.getName()), SystemRole::getName, params.getName());
        return super.page(Page.of(params.getCurrent(), params.getPageSize()), wrapper);
    }
}
