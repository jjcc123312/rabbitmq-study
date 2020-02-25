package com.jjcc.rabbitmq.routing.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息配置类
 * @author Jjcc
 * @version 1.0.0
 * @className rabbitRoutingPatternConfig.java
 * @createTime 2020年02月23日 20:27:00
 */
@Configuration
public class RabbitRoutingPatternConfig {

    /**
     * direct 类型的交换机。
     * @title directExchange
     * @author Jjcc
     * @return org.springframework.amqp.core.DirectExchange
     * @createTime 2020/2/23 0023 20:45
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("tut.direct", true, false);
    }

    /**
     * 队列A
     * @title queueA
     * @author Jjcc
     * @return org.springframework.amqp.core.Queue
     * @createTime 2020/2/23 0023 20:47
     */
    @Bean
    public Queue queueA() {
        return new Queue("queueA", true, false, false);
    }

    /**
     * 队列B
     * @title queueB
     * @author Jjcc
     * @return org.springframework.amqp.core.Queue
     * @createTime 2020/2/23 0023 20:47
     */
    @Bean
    public Queue queueB() {
        return new Queue("queueB", true, false, false);
    }

    /**
     * 交换机与队列A绑定，指定路由为error的binding。
     * @title bindingQaError
     * @author Jjcc
     * @param directExchange 交换机
     * @param queueA 队列A
     * @return org.springframework.amqp.core.Binding
     * @createTime 2020/2/23 0023 20:57
     */
    @Bean
    public Binding bindingQaError(DirectExchange directExchange, Queue queueA) {
        return BindingBuilder.bind(queueA).to(directExchange).with("error");
    }

    /**
     * 交换机与队列B绑定，指定路由为info的binding。
     * @title bindingQaError
     * @author Jjcc
     * @param directExchange 交换机
     * @param queueB 队列B
     * @return org.springframework.amqp.core.Binding
     * @createTime 2020/2/23 0023 20:57
     */
    @Bean
    public Binding bindingQbInfo(DirectExchange directExchange, Queue queueB) {
        return BindingBuilder.bind(queueB).to(directExchange).with("info");
    }

    /**
     * 交换机与队列B绑定，指定路由为error的binding。
     * @title bindingQbError
     * @author Jjcc
     * @param directExchange 交换机
     * @param queueB 队列B
     * @return org.springframework.amqp.core.Binding
     * @createTime 2020/2/23 0023 20:57
     */
    @Bean
    public Binding bindingQbError(DirectExchange directExchange, Queue queueB) {
        return BindingBuilder.bind(queueB).to(directExchange).with("error");
    }

    /**
     * 交换机与队列B绑定，指定路由为warning的binding。
     * @title bindingQbWarning
     * @author Jjcc
     * @param directExchange 交换机
     * @param queueB 队列B
     * @return org.springframework.amqp.core.Binding
     * @createTime 2020/2/23 0023 20:57
     */
    @Bean
    public Binding bindingQbWarning(DirectExchange directExchange, Queue queueB) {
        return BindingBuilder.bind(queueB).to(directExchange).with("warning");
    }

}




