package com.jjcc.rabbitmq.config;

import com.jjcc.rabbitmq.message.Demo01Message;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 添加 Direct Exchange 示例相关的 Exchange、Queue、Binding 的配置。
 * @author Administrator
 * @version 1.0.0
 * @description
 * @className RabbitConfig.java
 * @createTime 2020年02月19日 16:21:00
 */
@Configuration
public class RabbitConfig {

    /**
     * Direct Exchange示例的配置类
     */
    public static class DirectExchangeDemoConfiguration {

        /**
         * 创建消息队列。相当于一个容器。
         * @title demo01Queue
         * @author Jjcc
         * @return org.springframework.amqp.core.Queue
         * @createTime 2020/2/19 0019 16:41
         */
        @Bean
        public Queue demo01Queue() {
            /*
             para01：队列name
             para02：数据是否持久化
             para03：是否排它
             para04：是否自动删除
             */
            return new Queue(Demo01Message.QUEUE, true, false, false);
        }

        /**
         * 创建 Direct Exchange交换器。消息必须通过exchange发送到queue。
         * @title demo01Exchange
         * @author Jjcc
         * @return org.springframework.amqp.core.DirectExchange
         * @createTime 2020/2/19 0019 16:45
         */
        @Bean
        public DirectExchange demo01Exchange() {
            /*
             para01：交换器name
             para02：是否持久化
             para03：是否自动删除
             */
            return new DirectExchange(Demo01Message.EXCHANGE, true, false);
        }

        /**
         * 创建binding。
         * 消息发送 exchange时，一般指定一个 routing key，通过 binding 与 routing key相匹配后路由到对应的 Queue 中。
         * @title demo01Binding
         * @author Jjcc
         * @return org.springframework.amqp.core.Binding
         * @createTime 2020/2/19 0019 16:51
         */
        @Bean
        public Binding demo01Binding() {
            return BindingBuilder.bind(demo01Queue()).to(demo01Exchange()).with(Demo01Message.ROUTING_KEY);
        }
    }
}
