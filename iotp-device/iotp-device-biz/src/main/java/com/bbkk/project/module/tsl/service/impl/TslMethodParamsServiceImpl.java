package com.bbkk.project.module.tsl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbkk.project.module.tsl.entity.TslMethodParams;
import com.bbkk.project.module.tsl.mapper.TslMethodParamsMapper;
import com.bbkk.project.module.tsl.service.ITslMethodParamsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-05 21:15
 **/
@Service
public class TslMethodParamsServiceImpl extends ServiceImpl<TslMethodParamsMapper, TslMethodParams> implements ITslMethodParamsService {

    @Override
    public List<TslMethodParams> listByMethodId(String methodId) {
        LambdaQueryWrapper<TslMethodParams> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(TslMethodParams::getMethodId, methodId);
        return super.list(wrapper);
    }
}
