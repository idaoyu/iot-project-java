package com.bbkk.project.module.tsl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bbkk.project.module.tsl.entity.TslEnumValue;

import java.util.List;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-03 13:56
 **/
public interface ITslEnumValueService extends IService<TslEnumValue> {

    /**
     * 根据关联的id删除枚举值
     *
     * @param masterId 关联物模型中 属性/方法/事件 的id
     * @param source   来源
     * @return 成功返回 true
     */
    Boolean removeByMasterIdAndSource(String masterId, String source);

    /**
     * 根据关联的id查询枚举值
     *
     * @param masterId 关联物模型中 属性/方法/事件 的id
     * @return List<TslEnumValue>
     */
    List<TslEnumValue> listByMasterId(String masterId, String source);

    /**
     * 根据 id 更新描述
     *
     * @param description 新的描述信息
     * @param id          id
     * @return 成功返回 true
     */
    Boolean updateDescriptionById(String description, Long id);
}
