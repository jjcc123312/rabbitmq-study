package com.jjcc.rabbitmq.topic.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 配置类
 * @author Jjcc
 * @version 1.0.0
 * @className RabbitTopicPatternConfig.java
 * @createTime 2020年02月25日 16:58:00
 */
@Configuration
public class RabbitTopicPatternConfig {


//    Environment env;

    /**
     * 创建 Topic Exchange
     * @title topicExchange
     * @author Jjcc
     * @return org.springframework.amqp.core.TopicExchange
     * @createTime 2020/2/25 0025 17:00
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("tut.topic", true, false);
    }

    /**
     * 队列A
     * @title queueTopicA
     * @author Jjcc
     * @return org.springframework.amqp.core.Queue
     * @createTime 2020/2/25 0025 17:02
     */
    @Bean
    public Queue queueTopicA() {
        return new Queue("queue_topic_a", true, false, false);
    }

    /**
     * 对类B
     * @title queueTopicB
     * @author Jjcc
     * @return org.springframework.amqp.core.Queue
     * @createTime 2020/2/25 0025 17:03
     */
    @Bean
    public Queue queueTopicB() {
        return new Queue("queue_topic_b", true, false, false);
    }

    /**
     * 队列A与交换机绑定，指定路由键为 *.orange.* （通配符）
     * @title bindingTopicA
     * @author Jjcc
     * @param exchange 交换机
     * @param queueTopicA 队列A
     * @return org.springframework.amqp.core.Binding
     * @createTime 2020/2/25 0025 17:06
     */
    @Bean
    public Binding bindingTopicA(TopicExchange exchange, Queue queueTopicA) {
        return BindingBuilder.bind(queueTopicA).to(exchange).with("*.orange.*");
    }

    /**
     * 队列B与交换机绑定，指定路由键为 *.*.rabbit （通配符）
     * @title bindingTopicB
     * @author Jjcc
     * @param exchange 交换机
     * @param queueTopicB 队列B
     * @return org.springframework.amqp.core.Binding
     * @createTime 2020/2/25 0025 17:08
     */
    @Bean
    public Binding bindingTopicB(TopicExchange exchange, Queue queueTopicB) {
        return BindingBuilder.bind(queueTopicB).to(exchange).with("*.*.rabbit");
    }

    /**
     * 队列B与交换机绑定，指定路由键为 lazy.# （通配符）
     * @title bindingTopicC
     * @author Jjcc
     * @param exchange 交换机
     * @param queueTopicB 队列B
     * @return org.springframework.amqp.core.Binding
     * @createTime 2020/2/25 0025 17:08
     */
    @Bean
    public Binding bindingTopicC(TopicExchange exchange, Queue queueTopicB) {
        return BindingBuilder.bind(queueTopicB).to(exchange).with("lazy.#");
    }


}
