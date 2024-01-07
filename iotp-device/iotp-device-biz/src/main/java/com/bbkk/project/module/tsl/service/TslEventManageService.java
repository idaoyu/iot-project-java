package com.bbkk.project.module.tsl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bbkk.project.exception.BizException;
import com.bbkk.project.module.tsl.data.OperateTslEventParams;
import com.bbkk.project.module.tsl.data.PageGetTslEventParams;
import com.bbkk.project.module.tsl.data.PageGetTslEventVO;
import com.bbkk.project.module.tsl.entity.TslEvent;
import com.bbkk.project.module.tsl.entity.TslEventProperty;
import com.bbkk.project.module.tsl.entity.TslProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 物模型事件管理接口 业务逻辑
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-11 18:42
 **/
@Service
@RequiredArgsConstructor
public class TslEventManageService {

    private final ITslEventService tslEventService;
    private final ITslEventPropertyService tslEventPropertyService;
    private final ITslPropertyService tslPropertyService;

    @Transactional(rollbackFor = Exception.class)
    public String createTslEvent(OperateTslEventParams params) {
        // 校验入参的属性是否都存在
        checkPropertyExist(params.getPropertyIdList());
        TslEvent.TslEventBuilder builder = TslEvent.builder();
        builder.id(params.getId());
        builder.name(params.getName());
        builder.description(params.getDescription());
        builder.type(params.getType());
        builder.createTime(new Date());
        builder.updateTime(new Date());
        TslEvent tslEvent = builder.build();
        boolean saveEvent = tslEventService.save(builder.build());
        if (!saveEvent) {
            throw new BizException("创建物模型事件失败，请稍后重试");
        }
        for (String id : params.getPropertyIdList()) {
            TslEventProperty tslEventProperty = TslEventProperty.builder()
                    .eventId(tslEvent.getId())
                    .propertyId(id)
                    .build();
            boolean saveEventProperty = tslEventPropertyService.save(tslEventProperty);
            if (!saveEventProperty) {
                throw new BizException("创建物模型事件失败，请稍后重试");
            }
        }

        return "成功";
    }

    @Transactional(rollbackFor = Exception.class)
    public String removeTslEvent(String id) {
        boolean rmTslEvent = tslEventService.removeById(id);
        if (!rmTslEvent) {
            throw new BizException("删除物模型事件失败，请稍后重试");
        }
        boolean rmTslEventProperty = tslEventPropertyService.removeByEventId(id);
        if (!rmTslEventProperty) {
            throw new BizException("删除物模型事件失败，请稍后重试");
        }
        return "成功";
    }

    @Transactional(rollbackFor = Exception.class)
    public String updateTslEvent(OperateTslEventParams params) {
        // 校验入参的属性是否都存在
        checkPropertyExist(params.getPropertyIdList());

        TslEvent tslEvent = tslEventService
                .getOptById(params.getId()).orElseThrow(() -> new BizException("要修改的物模型事件不存在"));
        // 修改物模型事件表数据
        tslEvent.setName(params.getName());
        tslEvent.setDescription(params.getDescription());
        tslEvent.setType(params.getType());
        tslEvent.setUpdateTime(new Date());
        boolean update = tslEventService.updateById(tslEvent);
        if (!update) {
            throw new BizException("修改物模型事件失败，请稍后重试");
        }

        // 查询原有的关联数据
        List<TslEventProperty> tslEventPropertieList = tslEventPropertyService.listByEventId(params.getId());
        // 过滤出要删除的关联记录
        List<String> rmPropertyIdList = tslEventPropertieList.stream()
                .map(TslEventProperty::getPropertyId)
                .filter(propertyId -> !params.getPropertyIdList().contains(propertyId))
                .toList();
        if (!rmPropertyIdList.isEmpty()) {
            boolean rmSuccess = tslEventPropertyService.removeByEventIdAndPropertyIdList(params.getId(), rmPropertyIdList);
            if (!rmSuccess) {
                throw new BizException("修改物模型事件失败，请稍后重试");
            }
        }

        // 过滤出要新增的关联记录
        List<String> originalRelevanceList = tslEventPropertieList.stream().map(TslEventProperty::getPropertyId).toList();
        List<String> addProprrtyIdList = params.getPropertyIdList().stream().filter(v -> !originalRelevanceList.contains(v)).toList();
        if (!addProprrtyIdList.isEmpty()) {
            for (String propertyId : addProprrtyIdList) {
                TslEventProperty tslEventProperty = TslEventProperty.builder().eventId(params.getId()).propertyId(propertyId).build();
                boolean save = tslEventPropertyService.save(tslEventProperty);
                if (!save) {
                    throw new BizException("修改物模型事件失败，请稍后重试");
                }
            }
        }

        return "成功";
    }

    private void checkPropertyExist(List<String> propertyIdList) {
        for (String propertyId : propertyIdList) {
            // todo 后续重构成高效一点的写法
            tslPropertyService.getOptById(propertyId).orElseThrow(() -> new BizException("物模型属性：" + propertyId + " 不存在"));
        }
    }

    public IPage<PageGetTslEventVO> pageGetTslEvent(PageGetTslEventParams params) {
        IPage<TslEvent> page = tslEventService.pageGetTslEvent(params);
        return page.convert(v -> {
            PageGetTslEventVO.PageGetTslEventVOBuilder builder = PageGetTslEventVO.builder();
            builder.id(v.getId());
            builder.name(v.getName());
            builder.description(v.getDescription());
            builder.type(v.getType());
            builder.createTime(v.getCreateTime());
            builder.updateTime(v.getUpdateTime());
            List<String> propertyIdList = tslEventPropertyService.listPropertyIdByEventId(v.getId());
            List<TslProperty> tslPropertieList = tslPropertyService.listByIdList(propertyIdList);
            builder.propertyList(tslPropertieList);
            return builder.build();
        });
    }
}
