package com.bbkk.project.module.tsl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbkk.project.module.tsl.entity.TslEnumValue;
import com.bbkk.project.module.tsl.mapper.TslEnumValueMapper;
import com.bbkk.project.module.tsl.service.ITslEnumValueService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-03 13:57
 **/
@Service
public class TslEnumValueServiceImpl extends ServiceImpl<TslEnumValueMapper, TslEnumValue> implements ITslEnumValueService {

    @Override
    public Boolean removeByMasterIdAndSource(String masterId, String source) {
        LambdaQueryWrapper<TslEnumValue> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(TslEnumValue::getMasterId, masterId);
        wrapper.eq(TslEnumValue::getSource, source);
        boolean exists = super.exists(wrapper);
        if (!exists) {
            return true;
        }
        return super.remove(wrapper);
    }

    @Override
    public List<TslEnumValue> listByMasterId(String masterId, String source) {
        LambdaQueryWrapper<TslEnumValue> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(TslEnumValue::getMasterId, masterId);
        wrapper.eq(TslEnumValue::getSource, source);
        return super.list(wrapper);
    }

    @Override
    public Boolean updateDescriptionById(String description, Long id) {
        LambdaUpdateWrapper<TslEnumValue> wrapper = Wrappers.lambdaUpdate();
        wrapper.set(TslEnumValue::getDescription, description);
        wrapper.eq(TslEnumValue::getId, id);
        return super.update(wrapper);
    }
}
