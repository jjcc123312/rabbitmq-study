package com.jjcc.batch.consumer;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费端
 * @author Jjcc
 * @version 1.0.0
 * @className RabbitConsumer.java
 * @createTime 2020年02月26日 13:43:00
 */
@Component
@Log4j2
public class RabbitConsumer {

    /**
     * 普通消费端
     * @title receiver
     * @author Jjcc
     * @param message 消息。
     * @return void
     * @createTime 2020/2/26 0026 15:08
     */
    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue(value = "queue_concurrency", durable = "true", autoDelete = "false", exclusive = "false")
            , exchange = @Exchange(name = "exchange_concurrency", type = ExchangeTypes.TOPIC)
            , key = "concurrency.key")}, concurrency = "10")
    public void receiver(String message) throws InterruptedException {
        log.info("[消息接收者：{}][消息主题：{}]", Thread.currentThread().getId(), message);

        Thread.sleep(1000);

    }
}
