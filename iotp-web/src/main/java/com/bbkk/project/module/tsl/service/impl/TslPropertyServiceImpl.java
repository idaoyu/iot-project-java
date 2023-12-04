package com.bbkk.project.module.tsl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbkk.project.module.tsl.data.PageGetTslPropertyParams;
import com.bbkk.project.module.tsl.data.UpdateTslPropertyParams;
import com.bbkk.project.module.tsl.entity.TslProperty;
import com.bbkk.project.module.tsl.mapper.TslPropertyMapper;
import com.bbkk.project.module.tsl.service.ITslPropertyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-03 13:56
 **/
@Service
public class TslPropertyServiceImpl extends ServiceImpl<TslPropertyMapper, TslProperty> implements ITslPropertyService {

    @Override
    public IPage<TslProperty> pageByParams(PageGetTslPropertyParams params) {
        LambdaQueryWrapper<TslProperty> wrapper = Wrappers.lambdaQuery();
        wrapper.orderByDesc(TslProperty::getCreateTime);
        wrapper.like(StringUtils.isNotBlank(params.getName()), TslProperty::getName, params.getName());
        wrapper.eq(StringUtils.isNotBlank(params.getDataType()), TslProperty::getDataType, params.getDataType());
        return super.page(Page.of(params.getCurrent(), params.getPageSize()), wrapper);
    }

    @Override
    public Boolean updateTslProperty(TslProperty tslProperty, UpdateTslPropertyParams params, String id) {
        LambdaUpdateWrapper<TslProperty> wrapper = Wrappers.lambdaUpdate();
        wrapper.set(TslProperty::getName, params.getName());
        wrapper.set(TslProperty::getDescription, params.getDescription());
        wrapper.set(TslProperty::getDataType, params.getDataType());
        wrapper.set(TslProperty::getMaxValue, params.getMaxValue());
        wrapper.set(TslProperty::getMinValue, params.getMinValue());
        wrapper.set(TslProperty::getStepSize, params.getStepSize());
        wrapper.set(TslProperty::getUnit, params.getUnit());
        wrapper.set(TslProperty::getOnlyRead, params.getOnlyRead());
        wrapper.set(TslProperty::getUpdateTime, new Date());
        wrapper.eq(TslProperty::getId, id);
        return super.update(wrapper);
    }
}
