package com.jjcc.batch.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.amqp.rabbit.core.support.SimpleBatchingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

/**
 * 配置类;
 * 这里没有创建 exchange、queue、bindings。而是在接收端通过@RabbitListener()注解创建的。
 * @author Jjcc
 * @version 1.0.0
 * @className RabbitConfig.java
 * @createTime 2020年02月26日 13:42:00
 */
@Configuration
public class RabbitConfig {

    /**
     * 创建 BatchingRabbitTemplate Bean 对象。消息批量发送需要用到此对象。
     * BatchingRabbitTemplate类是RabbitTemplate的子类。
     * @title batchingRabbitTemplate
     * @author Jjcc
     * @param connectionFactory rabbitmq连接工厂对象
     * @return org.springframework.amqp.rabbit.core.BatchingRabbitTemplate
     * @createTime 2020/2/26 0026 14:23
     */
    @Bean(value = "batchingRabbitTemplate")
    public BatchingRabbitTemplate batchingRabbitTemplate(ConnectionFactory connectionFactory) {
        /*创建 batchingStrategy 对象，代表批量策略*/
        // 超过消息数量的最大条数
        int batchSize = 16384;
        // 每次批量发送消息的最大内存
        int bufferLimit = 33554432;
        // 超过收集的时间的最大等待时间；单位毫秒
        int timeout = 1000 * 30;
        SimpleBatchingStrategy simpleBatchingStrategy = new SimpleBatchingStrategy(batchSize, bufferLimit, timeout);

        // 创建定时器对象
        ConcurrentTaskScheduler concurrentTaskScheduler = new ConcurrentTaskScheduler();

        // 创建 BatchingRabbitTemplate 对象
        BatchingRabbitTemplate batchingRabbitTemplate = new BatchingRabbitTemplate(simpleBatchingStrategy, concurrentTaskScheduler);
        batchingRabbitTemplate.setConnectionFactory(connectionFactory);
        return batchingRabbitTemplate;
    }
}
