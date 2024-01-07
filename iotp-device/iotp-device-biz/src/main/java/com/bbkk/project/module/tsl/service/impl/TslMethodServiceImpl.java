package com.bbkk.project.module.tsl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbkk.project.module.tsl.data.PageGetTslMethodParams;
import com.bbkk.project.module.tsl.entity.TslMethod;
import com.bbkk.project.module.tsl.mapper.TslMethodMapper;
import com.bbkk.project.module.tsl.service.ITslMethodService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-05 21:14
 **/
@Service
public class TslMethodServiceImpl extends ServiceImpl<TslMethodMapper, TslMethod> implements ITslMethodService {

    @Override
    public IPage<TslMethod> pageGetTslMethod(PageGetTslMethodParams params) {
        LambdaQueryWrapper<TslMethod> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(StringUtils.isNotBlank(params.getId()), TslMethod::getId, params.getId());
        wrapper.like(StringUtils.isNotBlank(params.getName()), TslMethod::getName, params.getName());
        return super.page(Page.of(params.getCurrent(), params.getPageSize()), wrapper);
    }
}
