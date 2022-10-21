package com.txl.activemq.quartz;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import redis.clients.jedis.JedisPool;

import java.time.LocalDateTime;

/**
 * Created by T on 2022-10-19 09:04
 * ActiveMQ消息推送任务
 */
public class ActiveMqQuartzJob extends QuartzJobBean {
    protected static final Logger logger = LoggerFactory.getLogger(ActiveMqQuartzJob.class);

    private static final String CACHE_KEY = "senergy.subsystem.water.";
    private final JedisPool jedisPool;

    public ActiveMqQuartzJob(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) {
        logger.info("任务开始时间: [{}]", LocalDateTime.now());

        logger.info("任务结束: [{}]", LocalDateTime.now());
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }
}
