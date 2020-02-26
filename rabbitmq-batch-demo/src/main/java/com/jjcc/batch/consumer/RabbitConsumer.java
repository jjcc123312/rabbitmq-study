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
     * 支持自动声明绑定，声明之后自动监听队列的队列，此时@RabbitListener注解的queue和bindings不能同时指定，否则报错
     * @title receiver
     * @author Jjcc
     * @param message 消息。
     * @return void
     * @createTime 2020/2/26 0026 15:08
     */
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(name = "queue_batch_a", durable = "true"),
            exchange = @Exchange(value = "exchange_batch_a", type = ExchangeTypes.TOPIC), key = "rabbitmq.batch.demo")})
    public void receiver(String message) {
        log.info("[消息接收者：][消息主题：]{}" + message);
    }
}
