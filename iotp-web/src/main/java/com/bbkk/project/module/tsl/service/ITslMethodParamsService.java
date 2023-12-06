package com.bbkk.project.module.tsl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bbkk.project.module.tsl.entity.TslMethodParams;

import java.util.List;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-05 21:15
 **/
public interface ITslMethodParamsService extends IService<TslMethodParams> {

    /**
     * 根据 methodId 查询物模型方法参数
     *
     * @param methodId 物模型方法id
     * @return List<TslMethodParams>
     */
    List<TslMethodParams> listByMethodId(String methodId);

}
