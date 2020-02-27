package com.jjcc.batch.consumer;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Jjcc
 * @version 1.0.0
 * @className DeadRabbitConsumer.java
 * @createTime 2020年02月27日 16:13:00
 */
@Component
@Log4j2
public class DeadRabbitConsumer {

//    @RabbitListener(bindings = {
//            @QueueBinding(value = @Queue(name = "dead_queue_a", durable = "true"),
//                    exchange = @Exchange(value = "exchange_dead_a1"), key = "dead.routing.key")})
    @RabbitListener(queues = "dead_queue_a")
    public void deadReceiver(String message) {
        log.info("[onMessage][【死信队列】线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
