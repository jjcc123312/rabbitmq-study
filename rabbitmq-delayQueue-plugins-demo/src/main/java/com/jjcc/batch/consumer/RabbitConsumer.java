package com.jjcc.batch.consumer;

import lombok.extern.log4j.Log4j2;
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
//    @RabbitListener(queues = "common_queue_a")
//    public void receiver(String message) {
//        log.info("[消息接收者：][消息主题：]{}" + message);
//
//        // <X> 注意，此处抛出一个 RuntimeException 异常，模拟消费失败
////        throw new RuntimeException("我就是故意抛出一个异常");
//    }
}
