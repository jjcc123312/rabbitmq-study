package com.jjcc.batch.consumer;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 延迟消息 消费端
 * @author Jjcc
 * @version 1.0.0
 * @className DeadRabbitConsumer.java
 * @createTime 2020年02月27日 16:13:00
 */
@Component
@Log4j2
public class DelayRabbitConsumer {

    @RabbitListener(queues = "test_queue_1")
    public void deadReceiver(String message) {
        log.info("[onMessage][【延迟消息消费端】线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
