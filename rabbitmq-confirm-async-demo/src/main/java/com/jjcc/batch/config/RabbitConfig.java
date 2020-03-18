package com.jjcc.batch.config;

import com.jjcc.batch.core.RabbitProducerConfirmCallback;
import com.jjcc.batch.core.RabbitProducerReturnCallback;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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

   /**
    * 生产者的回调都在这里
    * @title rabbitTemplate
    * @author Jjcc
    * @param rabbitTemplate rabbit模板类
    * @param producerConfirmCallback 生产者的确认发送回调
    * @param producerReturnCallback 生产者的消息发送路由不到 queue 回调
    * @return org.springframework.amqp.rabbit.core.RabbitTemplate
    * @createTime 2020/3/18 14:08
    */
    @Autowired
    public RabbitTemplate rabbitTemplate(RabbitTemplate rabbitTemplate, RabbitProducerConfirmCallback producerConfirmCallback,
                                         RabbitProducerReturnCallback producerReturnCallback){
        //消息发送失败后返回到队列中
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setReturnCallback(producerReturnCallback);
        rabbitTemplate.setConfirmCallback(producerConfirmCallback);

        return rabbitTemplate;
    }

}





