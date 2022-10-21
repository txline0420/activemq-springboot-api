package com.txl.activemq.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Queue;

/**
 * Created By TangXiangLin on 2022-10-21 10:55
 * ActiveMQ配置文件
 */
@Configuration
public class ActiveMQConfig {
    private static final Logger logger = LoggerFactory.getLogger(ActiveMQConfig.class);

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Value("${spring.activemq.user}")
    private String userName;

    @Value("${spring.activemq.password}")
    private String password;

    @Bean
    public ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory factory
                = new ActiveMQConnectionFactory(userName, password, brokerUrl);
        logger.info("ActiveMQ连接创建成功. [{}]",factory.getBrokerURL());
        return factory;
    }

    @Bean
    public Queue queue() {
        ActiveMQQueue activeMQQueue = new ActiveMQQueue("sms.queue");
        try {
            logger.info("ActiveMQ队列服务创建成功. 推送的队列名称: [{}]",activeMQQueue.getQueueName());
        } catch (JMSException e) {
            logger.info("ActiveMQ队列服务创建失败.");
            throw new RuntimeException(e);
        }
        return activeMQQueue;
    }


}
