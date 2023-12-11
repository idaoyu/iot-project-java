package com.bbkk.project.module.tsl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bbkk.project.module.tsl.entity.TslEventProperty;

import java.util.List;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-11 18:41
 **/
public interface ITslEventPropertyService extends IService<TslEventProperty> {

    /**
     * 根据事件id删除关联数据
     *
     * @param eventId 事件id
     * @return 成功返回 true
     */
    boolean removeByEventId(String eventId);

    /**
     * 根据事件id查询关联数据
     *
     * @param eventId 事件id
     * @return List<TslEventProperty>
     */
    List<TslEventProperty> listByEventId(String eventId);

    /**
     * 根据事件id 和属性id list 删除符合条件的记录
     *
     * @param eventId    事件id
     * @param propertyId 属性id
     * @return 成功返回 true
     */
    boolean removeByEventIdAndPropertyIdList(String eventId, List<String> propertyId);

}
