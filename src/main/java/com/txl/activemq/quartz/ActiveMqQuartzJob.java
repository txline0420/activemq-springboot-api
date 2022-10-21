package com.txl.activemq.quartz;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.txl.activemq.activemq.bbms.BbmsDevice;
import com.txl.activemq.activemq.bbms.BbmsMapping;
import com.txl.activemq.activemq.bbms.BbmsResult;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;
import redis.clients.jedis.JedisPool;

import javax.jms.Queue;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * Created by T on 2022-10-19 09:04
 * ActiveMQ消息推送任务
 */
public class ActiveMqQuartzJob extends QuartzJobBean {
    protected static final Logger logger = LoggerFactory.getLogger(ActiveMqQuartzJob.class);

    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final String CACHE_KEY = "senergy.subsystem.water.";
    private final JedisPool jedisPool;
    private final Queue queue;
    private final JmsMessagingTemplate jmsMessagingTemplate;

    public ActiveMqQuartzJob(JedisPool jedisPool,
                             Queue queue, JmsMessagingTemplate jmsMessagingTemplate) {
        this.jedisPool = jedisPool;
        this.queue = queue;
        this.jmsMessagingTemplate = jmsMessagingTemplate;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) {
        logger.info("任务开始时间: [{}]", LocalDateTime.now());
        // 1. 获取所有的客户编码
        BbmsMapping bbmsMapping = new BbmsMapping();
        List<BbmsMapping> bbmsMappings = bbmsMapping.parseToListFromCSV();
        List<String> cIds = bbmsMappings.stream().map(BbmsMapping::getcId).collect(Collectors.toList());

        // 2. 构建消息
        List<BbmsDevice> bbmsDevices = new CopyOnWriteArrayList<>();
        for (String cId : cIds) {
            boolean isOnline = getIsOnline();
            boolean isInUse = getIsInUse(isOnline);
            boolean hasError = getHasError(isOnline, isInUse);
            List<String> eqpRunningStatuses = getEqpRunningStatuses(isOnline, isInUse);
            List<String> eqpErrorStatuses = getEqpErrorStatuses(hasError);
            BbmsDevice bbmsDevice = BbmsDevice.newBuilder()
                    .equipmentCode(cId)
                    .standCode(getStandCode(cId))
                    .isOnline(isOnline)
                    .isInUse(isInUse)
                    .hasError(hasError)
                    .eqpRunningStatuses(eqpRunningStatuses)
                    .eqpErrorStatuses(eqpErrorStatuses)
                    .reportTime(getReportTime())
                    .build();
            bbmsDevices.add(bbmsDevice);
        }
        BbmsResult bbmsResult = BbmsResult.newBuilder().bbmsDevices(bbmsDevices).build();

        // 3. 发送消息
        String msg = JSON.toJSONString(bbmsResult);
        logger.info("推送消息: {}",msg);
        this.jmsMessagingTemplate.convertAndSend(this.queue, msg);

        logger.info("任务结束: [{}]", LocalDateTime.now());
    }

    public String getReportTime(){
        LocalDateTime now = LocalDateTime.now();
        return now.format(df);
    }

    public List<String> getEqpErrorStatuses(boolean hasError){
        if(hasError) {
            return List.of("设备故障");
        }
        return null;
    }

    public List<String> getEqpRunningStatuses(boolean isOnline,boolean isInUse ){
        if(isOnline && isInUse){
            return List.of("正在登机");
        }
        return null;
    }

    public boolean getHasError(boolean isOnline,boolean isInUse){
        if(!isOnline && !isInUse){
            int num = RandomUtil.randomInt(0, 9);
            return num > 5;
        }
        return false;
    }

    public boolean getIsInUse(boolean isOnline){
        if(isOnline){
            int num = RandomUtil.randomInt(0, 9);
            return num > 6;
        }
        return false;
    }

    public boolean getIsOnline(){
        int num = RandomUtil.randomInt(0, 9);
        return num > 2;
    }

    public String getStandCode(String cId){
        String[] split = StringUtils.split(cId,"-");
        List<String> list = Arrays.asList(split);
        return list.get(1);
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public Queue getQueue() {
        return queue;
    }

    public JmsMessagingTemplate getJmsMessagingTemplate() {
        return jmsMessagingTemplate;
    }
}
