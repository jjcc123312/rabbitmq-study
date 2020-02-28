package com.jjcc.batch.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 配置类;
 * @author Jjcc
 * @version 1.0.0
 * @className RabbitConfig.java
 * @createTime 2020年02月26日 13:42:00
 */
@Configuration
public class RabbitConfig {

    /**
     * Queue
     * ttl()：设置队列的消息存活时间。
     * deadLetterExchange()：进入死信队列的交换机
     * deadLetterRotingKey()：进入死信队列的路由键
     * @title commonQueue
     * @author Jjcc
     * @return org.springframework.amqp.core.Queue
     * @createTime 2020/2/27 0027 21:53
     */
    @Bean
    public Queue commonQueue() {
        return QueueBuilder.durable("common_queue_a")
                .ttl(1000 * 10)
                .deadLetterExchange("exchange_delay_a")
                .deadLetterRoutingKey("delay.routing.key").build();
    }

    /**
     * 延迟队列
     * @title deadQueue
     * @author Jjcc
     * @return org.springframework.amqp.core.Queue
     * @createTime 2020/2/27 0027 21:55
     */
    @Bean
    public Queue delayQueue() {
        return new Queue("delay_queue_a", true, false, false);
    }

    /**
     * topic类型的交换机
     * @title topicExchange
     * @author Jjcc
     * @return org.springframework.amqp.core.TopicExchange
     * @createTime 2020/2/27 0027 21:55
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("exchange_delay_a", true, false);
    }

    /**
     * 普通消息队列与交换机的绑定
     * @title commonBinding
     * @author Jjcc
     * @param topicExchange 交换机对象
     * @param commonQueue 普通队列对象
     * @return org.springframework.amqp.core.Binding
     * @createTime 2020/2/27 0027 21:55
     */
    @Bean
    public Binding commonBinding(TopicExchange topicExchange, Queue commonQueue) {
        return BindingBuilder.bind(commonQueue).to(topicExchange).with("rabbitmq.common.demo");
    }

    /**
     * 延迟队列与交换机的绑定
     * @title deadQueueBinding
     * @author Jjcc
     * @param topicExchange 交换机对象
     * @param delayQueue 延迟队列对象
     * @return org.springframework.amqp.core.Binding
     * @createTime 2020/2/27 0027 21:56
     */
    @Bean
    public Binding deadQueueBinding(TopicExchange topicExchange, Queue delayQueue) {
        return BindingBuilder.bind(delayQueue).to(topicExchange).with("delay.routing.key");
    }
}
