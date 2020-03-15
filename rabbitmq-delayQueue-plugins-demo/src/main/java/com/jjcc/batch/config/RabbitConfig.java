package com.jjcc.batch.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;


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
     * 延时队列交换机；注意交换机的类型为：CustomExchange。
     * @title customExchange
     * @author Jjcc
     * @return org.springframework.amqp.core.CustomExchange
     * @createTime 2020/3/15 14:40
     */
    @Bean
    public CustomExchange customExchange() {
        HashMap<String, Object> map = new HashMap<>(4);
        map.put("x-delayed-type", "direct");
        return new CustomExchange("delay_plugins_exchange", "x-delayed-message", true, false, map);
    }

    /**
     * 队列
     * @title queue
     * @author Jjcc
     * @return org.springframework.amqp.core.Queue
     * @createTime 2020/3/15 14:46
     */
    @Bean
    public Queue queue() {
        return new Queue("test_queue_1", true, false, false);
    }

    /**
     * 消息队列绑定延迟交换机
     * @title binding
     * @author Jjcc
     * @param customExchange 延迟交换机
     * @param queue 消息队列
     * @return org.springframework.amqp.core.Binding
     * @createTime 2020/3/15 14:46
     */
    @Bean
    public Binding binding(CustomExchange customExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(customExchange).with("delay_key").noargs();
    }

}





