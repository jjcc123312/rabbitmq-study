package com.jjcc.rabbitmq.workqueuer.config;

import com.jjcc.rabbitmq.workqueuer.consumer.Demo02Consumer;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息配置类
 * @author Administrator
 * @version 1.0.0
 * @description
 * @className RabbitConfig.java
 * @createTime 2020年02月20日 14:19:00
 */
@Configuration
public class RabbitWorkQueueConfig {

    /**
     * 创建消息队列
     * @title queue
     * @author Jjcc
     * @return org.springframework.amqp.core.Queue
     * @createTime 2020/2/20 0020 14:21
     */
    @Bean
    public Queue queue() {
        return new Queue("queue_demo01");
    }

    /**
     * 静态内部类，定义了两个消息消费者。
     */
    public static class ReceiverConfig {
        @Bean
        public Demo02Consumer receiver1() {
            return new Demo02Consumer(1);
        }

        @Bean
        public Demo02Consumer receiver2() {
            return new Demo02Consumer(2);
        }

    }

    /**
     * 创建一个消息侦听器容器。
     * @title prefetchOneRabbitListenerContainerFactory
     * @author Jjcc
     * @param rabbitConnectionFactory 消息组件连接工厂
     * @return org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory<org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer>
     * @createTime 2020/2/20 0020 17:57
     */
    @Bean
    public RabbitListenerContainerFactory<SimpleMessageListenerContainer>
            prefetchOneRabbitListenerContainerFactory(ConnectionFactory rabbitConnectionFactory) {

        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setPrefetchCount(1);
        factory.setConnectionFactory(rabbitConnectionFactory);
        return factory;
    }
}
