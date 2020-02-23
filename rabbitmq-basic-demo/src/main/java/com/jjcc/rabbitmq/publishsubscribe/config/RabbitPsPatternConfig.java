package com.jjcc.rabbitmq.publishsubscribe.config;

import com.jjcc.rabbitmq.publishsubscribe.message.Message;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 * @author Administrator
 * @version 1.0.0
 * @description
 * @className RabbitPSPatternConfig.java
 * @createTime 2020年02月21日 16:47:00
 */
@Configuration
public class RabbitPsPatternConfig {

    /**
     * fanout 类型的 Exchange。
     * @title fanoutExchange
     * @author Jjcc
     * @return org.springframework.amqp.core.FanoutExchange
     * @createTime 2020/2/21 0021 17:02
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(Message.EXCHANGE, true, false);
    }

    /**
     * 创建 临时队列 A。
     * anonymousQueue：临时队列；消息非持久化的，与rabbitmq断开连接自动删除队列，队列名随机的。
     * @title autoDeleteQueueA
     * @author Jjcc
     * @return org.springframework.amqp.core.Queue
     * @createTime 2020/2/21 0021 17:03
     */
    @Bean
    public Queue autoDeleteQueueA() {
        return new AnonymousQueue();
    }

    /**
     * 创建 临时队列 B。
     * anonymousQueue：临时队列；消息非持久化的，与rabbitmq断开连接自动删除队列，队列名随机的。
     * @title autoDeleteQueueA
     * @author Jjcc
     * @return org.springframework.amqp.core.Queue
     * @createTime 2020/2/21 0021 17:03
     */
    @Bean
    public Queue autoDeleteQueueB() {
        return new AnonymousQueue();
    }

    /**
     * 队列与 exchange 绑定；fanoutExchange 不需要指定 routing key；
     * @title bindingA
     * @author Jjcc
     * @param fanoutExchange 上面创建的 fanout类型的Exchange
     * @param autoDeleteQueueA 队列A
     * @return org.springframework.amqp.core.Binding
     * @createTime 2020/2/21 0021 17:17
     */
    @Bean
    public Binding bindingA(FanoutExchange fanoutExchange, Queue autoDeleteQueueA) {
        return BindingBuilder.bind(autoDeleteQueueA).to(fanoutExchange);
    }

    /**
     * 队列与 exchange 绑定；fanoutExchange 不需要指定 routing key；
     * @title bindingB
     * @author Jjcc
     * @param fanoutExchange 上面创建的 fanout类型的Exchange
     * @param autoDeleteQueueB 队列B
     * @return org.springframework.amqp.core.Binding
     * @createTime 2020/2/21 0021 17:17
     */
    @Bean
    public Binding bindingB(FanoutExchange fanoutExchange, Queue autoDeleteQueueB) {
        return BindingBuilder.bind(autoDeleteQueueB).to(fanoutExchange);
    }
}




