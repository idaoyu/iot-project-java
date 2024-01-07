package com.bbkk.project.module.tsl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbkk.project.module.tsl.entity.TslEventProperty;
import com.bbkk.project.module.tsl.mapper.TslEventPropertyMapper;
import com.bbkk.project.module.tsl.service.ITslEventPropertyService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-11 18:41
 **/
@Service
public class TslEventPropertyServiceImpl extends ServiceImpl<TslEventPropertyMapper, TslEventProperty>
        implements ITslEventPropertyService {

    @Override
    public boolean removeByEventId(String eventId) {
        LambdaQueryWrapper<TslEventProperty> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(TslEventProperty::getEventId, eventId);
        return super.remove(wrapper);
    }

    @Override
    public List<TslEventProperty> listByEventId(String eventId) {
        LambdaQueryWrapper<TslEventProperty> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(TslEventProperty::getEventId, eventId);
        return super.list(wrapper);
    }

    @Override
    public boolean removeByEventIdAndPropertyIdList(String eventId, List<String> propertyId) {
        LambdaQueryWrapper<TslEventProperty> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(TslEventProperty::getEventId, eventId);
        wrapper.in(TslEventProperty::getPropertyId, propertyId);
        return super.remove(wrapper);
    }

    @Override
    public List<String> listPropertyIdByEventId(String eventId) {
        LambdaQueryWrapper<TslEventProperty> wrapper = Wrappers.lambdaQuery();
        wrapper.select(TslEventProperty::getPropertyId);
        wrapper.eq(TslEventProperty::getEventId, eventId);
        return super.list(wrapper).stream().map(TslEventProperty::getPropertyId).toList();
    }
}
