package com.bbkk.project.module.tsl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bbkk.project.module.tsl.data.PageGetTslPropertyParams;
import com.bbkk.project.module.tsl.data.UpdateTslPropertyParams;
import com.bbkk.project.module.tsl.entity.TslProperty;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-03 13:55
 **/
public interface ITslPropertyService extends IService<TslProperty> {

    /**
     * 分页查询
     *
     * @param params PageGetTslPropertyParams
     * @return IPage<TslProperty>
     */
    IPage<TslProperty> pageByParams(PageGetTslPropertyParams params);

    /**
     * 修改物模型属性
     *
     * @param tslProperty 原来的物模型属性
     * @param params      修改入参
     * @param id          要修改的 id
     * @return 成功返回 true
     */
    Boolean updateTslProperty(TslProperty tslProperty, UpdateTslPropertyParams params, String id);

}
