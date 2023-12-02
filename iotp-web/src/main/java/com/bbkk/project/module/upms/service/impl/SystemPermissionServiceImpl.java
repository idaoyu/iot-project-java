package com.bbkk.project.module.upms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbkk.project.module.upms.data.PageGetPermissionParams;
import com.bbkk.project.module.upms.entity.SystemPermission;
import com.bbkk.project.module.upms.mapper.SystemPermissionMapper;
import com.bbkk.project.module.upms.service.ISystemPermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-29 19:00
 **/
@Service
public class SystemPermissionServiceImpl extends ServiceImpl<SystemPermissionMapper, SystemPermission>
        implements ISystemPermissionService {

    @Override
    public IPage<SystemPermission> pageGetPermission(PageGetPermissionParams params) {
        LambdaQueryWrapper<SystemPermission> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StringUtils.isNotBlank(params.getName()), SystemPermission::getName, params.getName());
        return super.page(Page.of(params.getCurrent(), params.getPageSize()), wrapper);
    }
}
