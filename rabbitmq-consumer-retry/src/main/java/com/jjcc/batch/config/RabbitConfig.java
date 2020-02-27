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
     * deadLetterExchange()：进入死信队列的交换机
     * deadLetterRotingKey()：进入死信队列的路由键
     * @title commonQueue
     * @author Jjcc
     * @return org.springframework.amqp.core.Queue
     * @createTime 2020/2/27 0027 21:53
     */
    @Bean
    public Queue commonQueue() {
        return QueueBuilder.durable("retry_queue")
                .deadLetterExchange("exchange_dead_a")
                .deadLetterRoutingKey("dead.routing.key").build();
    }

    /**
     * 死信队列
     * @title deadQueue
     * @author Jjcc
     * @return org.springframework.amqp.core.Queue
     * @createTime 2020/2/27 0027 21:55
     */
    @Bean
    public Queue deadQueue() {
        return new Queue("dead_queue_a", true, false, false);
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
        return new TopicExchange("exchange_dead_a", true, false);
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
        return BindingBuilder.bind(commonQueue).to(topicExchange).with("rabbitmq.batch.demo");
    }

    /**
     * 死信队列与交换机的绑定
     * @title deadQueueBinding
     * @author Jjcc
     * @param topicExchange 交换机对象
     * @param deadQueue 死信队列对象
     * @return org.springframework.amqp.core.Binding
     * @createTime 2020/2/27 0027 21:56
     */
    @Bean
    public Binding deadQueueBinding(TopicExchange topicExchange, Queue deadQueue) {
        return BindingBuilder.bind(deadQueue).to(topicExchange).with("dead.routing.key");
    }
}
