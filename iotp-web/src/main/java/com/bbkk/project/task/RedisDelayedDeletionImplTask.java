package com.bbkk.project.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * 对 redis 延迟双删逻辑的 延时实现
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-16 19:18
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class RedisDelayedDeletionImplTask extends QuartzJobBean {

    private final RedissonClient redissonClient;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String targetDeleteKey = context.getTrigger().getJobDataMap().getString("redisKey");
        RBucket<Object> bucket = redissonClient.getBucket(targetDeleteKey);
        bucket.delete();
    }
}
