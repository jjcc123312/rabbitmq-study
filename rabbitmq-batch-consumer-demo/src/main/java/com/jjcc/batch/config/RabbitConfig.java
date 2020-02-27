package com.jjcc.batch.config;

import org.springframework.amqp.rabbit.batch.SimpleBatchingStrategy;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;


/**
 * 配置类;
 * 这里没有定义 exchange、queue、bindings。而是在接收端通过@RabbitListener()注解创建的。
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
        int timeout = 1000 * 5;
        SimpleBatchingStrategy simpleBatchingStrategy = new SimpleBatchingStrategy(batchSize, bufferLimit, timeout);

        // 创建定时器对象
        ConcurrentTaskScheduler concurrentTaskScheduler = new ConcurrentTaskScheduler();

        // 创建 BatchingRabbitTemplate 对象
        BatchingRabbitTemplate batchingRabbitTemplate = new BatchingRabbitTemplate(simpleBatchingStrategy, concurrentTaskScheduler);
        batchingRabbitTemplate.setConnectionFactory(connectionFactory);
        return batchingRabbitTemplate;
    }

    /**
     * 创建一个消息侦听器容器。
     * 用于管理 RabbitMQ监听器listener 的容器工厂；这里设置成支持批量消费。
     * @title simpleRabbitListenerContainerFactory
     * @author Jjcc
     * @param configurer  未知的参数
     * @param connectionFactory rabbitmq连接工厂对象
     * @return org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
     * @createTime 2020/2/26 0026 22:08
     */
    @Bean("consumerBatchContainerFactory")
    public SimpleRabbitListenerContainerFactory consumerBatchContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        // 创建 SimpleRabbitListenerContainerFactory 对象
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();

        configurer.configure(factory, connectionFactory);

        // 配置消费者的监听器是批量消费消息的类型。
        // 这里 amqp 版本：2.2.1.RELEASE。2.1.X版本无此方法。
        factory.setBatchListener(true);

        return factory;
    }
}
