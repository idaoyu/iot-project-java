package com.bbkk.project.module.tsl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bbkk.project.exception.BizException;
import com.bbkk.project.module.tsl.constant.DataTypeConstant;
import com.bbkk.project.module.tsl.constant.EnumValueSourceConstant;
import com.bbkk.project.module.tsl.convert.TslMethodConvert;
import com.bbkk.project.module.tsl.data.*;
import com.bbkk.project.module.tsl.entity.TslEnumValue;
import com.bbkk.project.module.tsl.entity.TslMethod;
import com.bbkk.project.module.tsl.entity.TslMethodParams;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.bbkk.project.module.tsl.constant.DataTypeConstant.ENUM;

/**
 * 物模型方法管理接口 业务逻辑
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-05 21:20
 **/
@Service
@RequiredArgsConstructor
public class TslMethodManageService {

    private final ITslMethodService tslMethodService;
    private final ITslMethodParamsService tslMethodParamsService;
    private final ITslEnumValueService tslEnumValueService;
    private final TslMethodConvert tslMethodConvert;


    @Transactional(rollbackFor = Exception.class)
    public String createTslMethod(OperateTslMethodParams params) {
        boolean exists = tslMethodService
                .exists(Wrappers.lambdaQuery(TslMethod.class).eq(TslMethod::getId, params.getId()));
        if (exists) {
            throw new BizException("物模型方法id已存在");
        }
        TslMethod tslMethod = params2TslMethod(params);
        boolean saveMethod = tslMethodService.save(tslMethod);
        if (!saveMethod) {
            throw new BizException("创建物模型方法失败，请稍后重试");
        }
        for (OperateTslMethodParamsDTO dto : params.getParamsList()) {
            saveTslMethodParams(tslMethod.getId(), dto);
        }
        return "成功";
    }

    @Transactional(rollbackFor = Exception.class)
    public String removeTslMethod(String id) {
        tslMethodService.getOptById(id).orElseThrow(() -> new BizException("要删除的物模型方法不存在"));
        boolean rmMethod = tslMethodService.removeById(id);
        if (!rmMethod) {
            throw new BizException("删除物模型方法失败，请稍后重试");
        }
        List<TslMethodParams> tslMethodParamsList = tslMethodParamsService.listByMethodId(id);
        // 虽然直接根据 methodId 删除很高效 但是暂时想不出来什么方法能够连带删除枚举
        for (TslMethodParams tslMethodParams : tslMethodParamsList) {
            if (DataTypeConstant.ENUM.getDataType().equals(tslMethodParams.getDataType())) {
                // 删除方法参数配置的枚举
                Boolean rmEnumValue = tslEnumValueService
                        .removeByMasterIdAndSource(tslMethodParams.getId() + "", EnumValueSourceConstant.METHOD_PARAMS.getSource());
                if (!rmEnumValue) {
                    throw new BizException("删除物模型方法失败，请稍后重试");
                }
            }
            boolean rmTslMethodParams = tslMethodParamsService.removeById(tslMethodParams.getId());
            if (!rmTslMethodParams) {
                throw new BizException("删除物模型方法失败，请稍后重试");
            }
        }
        return "成功";
    }

    @Transactional(rollbackFor = Exception.class)
    public String updateTslMethod(OperateTslMethodParams params) {
        TslMethod tslMethod = tslMethodService.getOptById(params.getId()).orElseThrow(() -> new BizException("要修改的属性不存在"));
        // 设置新的 tsl_method 表数据
        tslMethod.setName(params.getName());
        tslMethod.setDescription(params.getDescription());
        tslMethod.setAsynchronous(params.getAsynchronous());
        tslMethod.setUpdateTime(new Date());
        boolean success = tslMethodService.updateById(tslMethod);
        if (!success) {
            throw new BizException("修改物模型方法失败，请稍后重试");
        }
        //  ===== 对方法的参数进行处理 =====

        // 查询原始 物模型方法参数
        List<TslMethodParams> tslMethodParamsList = tslMethodParamsService.listByMethodId(tslMethod.getId());
        // 使用 identifier + type 作为 map 的 key 下面统称为 key
        Map<String, TslMethodParams> map = tslMethodParamsList.stream()
                .collect(Collectors.toMap(v -> v.getIdentifier() + v.getType(), v -> v));
        // 用来存放 被处理过的 key 方便后续删除的时候知道那个是要删除的
        List<String> handlerParamsTag = Lists.newArrayList();
        // 遍历接口入参中的 物模型方法参数
        for (OperateTslMethodParamsDTO dto : params.getParamsList()) {
            // 先将入参对象转换为 do 对象
            TslMethodParams tslMethodParams = tslMethodConvert.operateTslMethodParamsDto2TslMethodParams(dto);
            // 如果数据库中不存在存在该数据 则为新增
            if (!map.containsKey(dto.getIdentifier() + dto.getType())) {
                tslMethodParams.setMethodId(tslMethod.getId());
                boolean save = tslMethodParamsService.save(tslMethodParams);
                if (!save) {
                    throw new BizException("修改物模型方法失败，请稍后重试");
                }
                // 如果是枚举类型 去新增枚举
                if (dto.getDataType().equals(DataTypeConstant.ENUM.getDataType())) {
                    saveEnumValue(dto, tslMethodParams.getId());
                }
                // 跳出当前循环，不执行后续逻辑
                continue;
            }
            // 下面为 修改 物模型参数 逻辑
            // 通过 key 获取数据库中原有的数据
            TslMethodParams source = map.get(dto.getIdentifier() + dto.getType());
            // 判断数据类型是否有修改，并且和枚举相关，并处理
            handlerEnumUpdate(source, dto);

            // 设置新的值
            source.setName(dto.getName());
            source.setDescription(dto.getDescription());
            source.setType(dto.getType());
            source.setDataType(dto.getDataType());
            source.setMinValue(dto.getMinValue());
            source.setMaxValue(dto.getMaxValue());
            source.setStepSize(dto.getStepSize());
            source.setUnit(dto.getUnit());
            // 如果从非枚举类型改为枚举类型，将无用的字段置为空
            if (!source.getDataType().equals(ENUM.getDataType()) && tslMethodParams.getDataType().equals(ENUM.getDataType())) {
                source.setMinValue(null);
                source.setMaxValue(null);
                source.setStepSize(null);
                source.setUnit(null);
            }
            // 更新 物模型方法参数
            boolean updateParams = tslMethodParamsService.updateById(source);
            if (!updateParams) {
                throw new BizException("修改物模型方法失败，请稍后重试");
            }
            // 处理过这个 key 了 把他放到 list 里面
            handlerParamsTag.add(dto.getIdentifier() + dto.getType());
        }
        // 根据 handlerParamsTag 过滤出 数据库中有的 但是没有被处理的 则为要删除的
        List<TslMethodParams> removeList = tslMethodParamsList.stream()
                .filter(v -> !handlerParamsTag.contains(v.getIdentifier() + v.getType())).toList();
        // 循环把这些玩应删喽
        for (TslMethodParams tslMethodParams : removeList) {
            // 如果要删除的参数是枚举类型，则把枚举值配置也删掉
            if (tslMethodParams.getDataType().equals(ENUM.getDataType())) {
                Boolean rmEnumValue = tslEnumValueService.removeByMasterIdAndSource(tslMethodParams.getId() + "",
                        EnumValueSourceConstant.METHOD_PARAMS.getSource());
                if (!rmEnumValue) {
                    throw new BizException("修改物模型方法失败，请稍后重试");
                }
            }
            boolean rmSuccess = tslMethodParamsService.removeById(tslMethodParams.getId());
            if (!rmSuccess) {
                throw new BizException("修改物模型方法失败，请稍后重试");
            }
        }

        return "成功";
    }

    /**
     * 将 OperateTslMethodParams 对象转换为 TslMethod
     *
     * @param params OperateTslMethodParams
     * @return TslMethod
     */
    private TslMethod params2TslMethod(OperateTslMethodParams params) {
        TslMethod.TslMethodBuilder builder = TslMethod.builder();
        builder.id(params.getId());
        builder.name(params.getName());
        builder.description(params.getDescription());
        builder.asynchronous(params.getAsynchronous());
        builder.createTime(new Date());
        builder.updateTime(new Date());
        return builder.build();
    }

    /**
     * 保存 tsl_method_params 表数据
     *
     * @param methodId     方法的id
     * @param methodParams 参数入参
     */
    private void saveTslMethodParams(String methodId, OperateTslMethodParamsDTO methodParams) {
        TslMethodParams tslMethodParams = tslMethodConvert
                .operateTslMethodParamsDto2TslMethodParams(methodParams);
        tslMethodParams.setMethodId(methodId);
        boolean saveMethodParams = tslMethodParamsService.save(tslMethodParams);
        if (!saveMethodParams) {
            throw new BizException("创建物模型方法失败，请稍后重试");
        }
        if (methodParams.getDataType().equals(DataTypeConstant.ENUM.getDataType())) {
            // 如果是枚举类型
            saveEnumValue(methodParams, tslMethodParams.getId());
        }
    }

    /**
     * 保存枚举数据
     *
     * @param dto      OperateTslMethodParamsDTO
     * @param masterId 枚举绑定的参数 id
     */
    private void saveEnumValue(OperateTslMethodParamsDTO dto, Long masterId) {
        if (!dto.getDataType().equals(DataTypeConstant.ENUM.getDataType())) {
            throw new BizException("方法调用错误，不应在数据类型为非枚举值时调用保存枚举值方法");
        }
        for (OperateTslEnumValueParams enumValueParams : dto.getEnumValueParamsList()) {
            TslEnumValue.TslEnumValueBuilder builder = TslEnumValue.builder();
            builder.masterId(masterId + "");
            builder.source(EnumValueSourceConstant.METHOD_PARAMS.getSource());
            builder.value(enumValueParams.getValue());
            builder.description(enumValueParams.getDescription());
            boolean saveEnumValue = tslEnumValueService.save(builder.build());
            if (!saveEnumValue) {
                throw new BizException("保存枚举值时出现错误，请稍后重试");
            }
        }
    }

    /**
     * 处理枚举值相关的修改
     *
     * @param source    数据库中的修改前数据
     * @param newParams 要修改的参数
     */
    private void handlerEnumUpdate(TslMethodParams source, OperateTslMethodParamsDTO newParams) {
        if (ENUM.getDataType().equals(source.getDataType()) && !newParams.getDataType().equals(ENUM.getDataType())) {
            // 如果修改之前是枚举 修改后不是枚举
            Boolean success = tslEnumValueService.removeByMasterIdAndSource(source.getId() + ""
                    , EnumValueSourceConstant.METHOD_PARAMS.getSource());
            if (!success) {
                throw new BizException("修改物模型方法失败，请稍后重试");
            }
            return;
        } else if (!ENUM.getDataType().equals(source.getDataType()) && newParams.getDataType().equals(ENUM.getDataType())) {
            // 如果修改之前不是枚举 修改后是枚举
            for (OperateTslEnumValueParams enumValueParams : newParams.getEnumValueParamsList()) {
                TslEnumValue.TslEnumValueBuilder builder = TslEnumValue.builder();
                builder.masterId(source.getId() + "");
                builder.source(EnumValueSourceConstant.METHOD_PARAMS.getSource());
                builder.value(enumValueParams.getValue());
                builder.description(enumValueParams.getDescription());
                boolean success = tslEnumValueService.save(builder.build());
                if (!success) {
                    throw new BizException("修改物模型方法失败，请稍后重试");
                }
            }
            return;
        } else if (!ENUM.getDataType().equals(source.getDataType()) && !newParams.getDataType().equals(ENUM.getDataType())) {
            // 原来不是现在也不是
            return;
        }
        // 类型没有修改 但是内容可能被修改
        List<TslEnumValue> list = tslEnumValueService.listByMasterIdAndSource(source.getId() + "",
                EnumValueSourceConstant.METHOD_PARAMS.getSource());
        Map<String, TslEnumValue> map = list.stream().collect(Collectors.toMap(TslEnumValue::getValue, v -> v));
        List<String> newValueList = Lists.newArrayList();
        for (OperateTslEnumValueParams enumValueParams : newParams.getEnumValueParamsList()) {
            TslEnumValue tslEnumValue = map.get(enumValueParams.getValue());
            if (tslEnumValue == null) {
                // 原数据中不包含新数据 则新增
                TslEnumValue.TslEnumValueBuilder builder = TslEnumValue.builder();
                builder.masterId(source.getId() + "");
                builder.source(EnumValueSourceConstant.METHOD_PARAMS.getSource());
                builder.value(enumValueParams.getValue());
                builder.description(enumValueParams.getDescription());
                boolean success = tslEnumValueService.save(builder.build());
                if (!success) {
                    throw new BizException("修改物模型方法失败，请稍后重试");
                }
            } else if (!tslEnumValue.getDescription().equals(enumValueParams.getDescription())) {
                // 如果 value 相同 但 description 不相同 则更新 description
                Boolean success = tslEnumValueService.updateDescriptionById(enumValueParams.getDescription(), tslEnumValue.getId());
                if (!success) {
                    throw new BizException("修改物模型方法失败，请稍后重试");
                }
            }
            newValueList.add(enumValueParams.getValue());
        }
        // 过滤出需要删除的
        List<TslEnumValue> waitRemoveEnumValueList = list.stream().filter(v -> !newValueList.contains(v.getValue())).toList();
        if (!waitRemoveEnumValueList.isEmpty()) {
            boolean success = tslEnumValueService.
                    removeBatchByIds(waitRemoveEnumValueList.stream().map(TslEnumValue::getId).toList());
            if (!success) {
                throw new BizException("修改物模型方法失败，请稍后重试");
            }
        }
    }

    public IPage<PageGetTslMethodVO> pageGetTslMethod(PageGetTslMethodParams params) {
        // 这地方其实用 xml 更方便，但是分页时会有问题
        IPage<TslMethod> page = tslMethodService.pageGetTslMethod(params);
        return page.convert(v -> {
            PageGetTslMethodVO vo = tslMethodConvert.tslMethod2PageGetTslMethodVO(v);
            // 根据 id 查询方法参数
            List<TslMethodParams> tslMethodParamsList = tslMethodParamsService.listByMethodId(vo.getId());
            List<PageGetTslMethodParamsVO> list = tslMethodParamsList
                    .stream()
                    .map(tslMethodConvert::tslMethodParams2PageGetTslMethodParamsVO)
                    .filter(p -> {
                        // 如果是枚举类型，去查询对应的枚举值
                        if (p.getDataType().equals(ENUM.getDataType())) {
                            List<TslEnumValue> enumValueList = tslEnumValueService.listByMasterIdAndSource(
                                    p.getId() + "", EnumValueSourceConstant.METHOD_PARAMS.getSource()
                            );
                            p.setEnumValueList(enumValueList);
                        }
                        return true;
                    })
                    .toList();
            vo.setParamsList(list);
            return vo;
        });
    }
}
