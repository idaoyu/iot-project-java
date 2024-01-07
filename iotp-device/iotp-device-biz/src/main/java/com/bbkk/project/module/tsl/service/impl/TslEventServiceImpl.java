package com.bbkk.project.module.tsl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbkk.project.module.tsl.data.PageGetTslEventParams;
import com.bbkk.project.module.tsl.entity.TslEvent;
import com.bbkk.project.module.tsl.mapper.TslEventMapper;
import com.bbkk.project.module.tsl.service.ITslEventService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-11 18:40
 **/
@Service
public class TslEventServiceImpl extends ServiceImpl<TslEventMapper, TslEvent> implements ITslEventService {

    @Override
    public IPage<TslEvent> pageGetTslEvent(PageGetTslEventParams params) {
        LambdaQueryWrapper<TslEvent> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(StringUtils.isNotBlank(params.getId()), TslEvent::getId, params.getId());
        return super.page(Page.of(params.getCurrent(), params.getPageSize()), wrapper);
    }
}
