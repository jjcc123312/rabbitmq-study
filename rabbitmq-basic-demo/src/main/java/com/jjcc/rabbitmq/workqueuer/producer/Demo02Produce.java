package com.jjcc.rabbitmq.workqueuer.producer;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 * @version 1.0.0
 * @description
 * @className Demo02Produce.java
 * @createTime 2020年02月20日 14:35:00
 */
@Component
@Log4j2
public class Demo02Produce {

    @Autowired
    private RabbitTemplate template;


    private AtomicInteger dots = new AtomicInteger();
    private AtomicInteger count = new AtomicInteger();

    /**
     * 消息发布者
     * @title send
     * @author Jjcc
     * @return void
     * @createTime 2020/2/20 0020 14:38
     */
    public void send() {
        StringBuilder sb = new StringBuilder("hello");
        if (dots.getAndIncrement() == 3) {
            dots.set(1);
        }

        for (int i = 0; i < dots.get(); i++) {
            sb.append(".");
        }
        sb.append(count.incrementAndGet());
        template.convertAndSend("queue_demo01", sb.toString());
        log.info(" [x] Sent '" + sb.toString() + "'");
    }

}
