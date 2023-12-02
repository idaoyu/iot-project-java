package com.bbkk.project.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.bbkk.project.constant.RedisCacheKeyConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * redis 工具类
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-16 19:04
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class RedisUtil {

    /**
     * 默认时间单位
     */
    private static final DateField DEFAULT_DATE_FIELD = DateField.SECOND;
    /**
     * 默认时间计数
     */
    private static final Integer OFFSET = 3;

    private final RedissonClient redissonClient;
    private final Scheduler scheduler;
    private final JobDetail redisDelayedDeletionImplTaskDetail;

    /**
     * redis 延时双删工具类
     *
     * @param constant 缓存 key
     * @param suffix   如果存在 拼接在 key 后面的后缀
     */
    public void delayedDeletion(RedisCacheKeyConstant constant, String suffix) {
        delayedDeletion(constant, suffix, DEFAULT_DATE_FIELD, OFFSET);
    }

    public void delayedDeletionMultiple(List<RedisCacheKeyConstant> constants, List<String> suffixList) {
        for (int i = 0; i < constants.size(); i++) {
            delayedDeletion(constants.get(i), suffixList == null ? null : suffixList.get(i), DEFAULT_DATE_FIELD, OFFSET);
        }
    }

    /**
     * redis 延时双删
     *
     * @param constant  缓存 key
     * @param suffix    如果存在 拼接在 key 后面的后缀
     * @param dateField 延时的时间单位
     * @param offset    延时的时间计数
     */
    private void delayedDeletion(RedisCacheKeyConstant constant, String suffix, DateField dateField, Integer offset) {
        // 构造要删除的 redis key
        String targetRemoveRedisKey = constant.getKey() + (StringUtils.isNotBlank(suffix) ? suffix : "");
        Date currentDate = new Date();
        // 删除 redis 中的 key
        RBucket<Object> bucket = redissonClient.getBucket(targetRemoveRedisKey);
        bucket.delete();
        // 设置执行时间
        DateTime startTime = DateUtil.offset(currentDate, dateField, offset);
        // 构造触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(redisDelayedDeletionImplTaskDetail)
                // 设置任务的开始执行时间
                .startAt(startTime)
                .build();
        // 设置传入定时任务的参数
        trigger.getJobDataMap().put("redisKey", targetRemoveRedisKey);
        try {
            // 交给 quartz 调度
            scheduler.scheduleJob(trigger);
        } catch (Exception ex) {
            log.error("执行 redis 延时双删时产生异常。要删除的 key: {} ", 1);
            log.error("执行 redis 延时双删时产生异常", ex);
        }
    }


}
