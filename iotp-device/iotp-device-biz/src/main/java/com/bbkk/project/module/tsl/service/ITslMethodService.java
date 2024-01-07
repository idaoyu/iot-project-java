package com.bbkk.project.module.tsl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bbkk.project.module.tsl.data.PageGetTslMethodParams;
import com.bbkk.project.module.tsl.entity.TslMethod;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-05 21:14
 **/
public interface ITslMethodService extends IService<TslMethod> {

    /**
     * 分页查询物模型方法
     *
     * @param params PageGetTslMethodParams
     * @return IPage<TslMethod>
     */
    IPage<TslMethod> pageGetTslMethod(PageGetTslMethodParams params);
}
