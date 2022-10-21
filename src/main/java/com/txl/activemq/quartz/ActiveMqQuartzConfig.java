package com.txl.activemq.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by T on 2022-10-19 09:12
 * ActiveMQ定时任务配置文件
 */
@Configuration
public class ActiveMqQuartzConfig {

    @Bean
    public JobDetail activeMqJobDetail(){
        return JobBuilder.newJob(ActiveMqQuartzJob.class).storeDurably().build();
    }

    @Bean
    public Trigger activeMqTrigger(){
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 */1 * * * ?");
        return TriggerBuilder.newTrigger()
                .withIdentity("activeMq", "mq")
                .forJob(activeMqJobDetail())
                .withSchedule(cronScheduleBuilder)
                .build();
    }
}
