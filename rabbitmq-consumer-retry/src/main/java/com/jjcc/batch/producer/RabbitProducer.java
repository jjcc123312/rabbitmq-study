package com.jjcc.batch.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Jjcc
 * @version 1.0.0
 * @className RabbitProducer.java
 * @createTime 2020年02月26日 14:24:00
 */
@Component
public class RabbitProducer {

    private RabbitTemplate rabbitTemplate;


    @Autowired
    public RabbitProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }



    private AtomicLong count = new AtomicLong();

    /**
     * 发送消息；消息批量发送需要使用 BatchingRabbitTemplate对象。
     * @title send
     * @author Jjcc
     * @return void
     * @createTime 2020/2/26 0026 14:30
     */
    @Async
    public void send() {
        String s = "Hello World!!!：" + count.incrementAndGet();
        rabbitTemplate.convertAndSend("exchange_dead_a", "rabbitmq.batch.demo", s);
    }
}
