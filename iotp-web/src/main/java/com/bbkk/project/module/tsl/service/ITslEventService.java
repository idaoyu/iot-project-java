package com.bbkk.project.module.tsl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bbkk.project.module.tsl.data.PageGetTslEventParams;
import com.bbkk.project.module.tsl.entity.TslEvent;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-11 18:39
 **/
public interface ITslEventService extends IService<TslEvent> {

    /**
     * 分页查询物模型事件
     *
     * @param params PageGetTslEventParams
     * @return IPage<PageGetTslEventVO>
     */
    IPage<TslEvent> pageGetTslEvent(PageGetTslEventParams params);
}
