package com.bbkk.project.task.config;

import com.bbkk.project.task.RedisDelayedDeletionImplTask;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Quartz 配置类
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-16 19:33
 **/
@Configuration
public class QuartzConfig {

    @Bean(name = "redisDelayedDeletionImplTaskDetail")
    public JobDetail redisDelayedDeletionImplTaskDetail() {
        return JobBuilder.newJob(RedisDelayedDeletionImplTask.class).storeDurably().build();
    }

}
