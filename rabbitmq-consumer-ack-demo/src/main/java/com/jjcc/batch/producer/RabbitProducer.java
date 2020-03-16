package com.jjcc.batch.producer;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Jjcc
 * @version 1.0.0
 * @className RabbitProducer.java
 * @createTime 2020年02月26日 14:24:00
 */
@Component
@Log4j2
public class RabbitProducer {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private AtomicLong count = new AtomicLong();

    /**
     * @title send
     * @author Jjcc
     * @return void
     * @createTime 2020/2/28 0028 17:36
     */
    public void send(Integer id) {
        rabbitTemplate.convertAndSend("exchange_concurrency", "concurrency.key", id);
        log.info("消息发送完成！！！！！！！！！");
    }
}
