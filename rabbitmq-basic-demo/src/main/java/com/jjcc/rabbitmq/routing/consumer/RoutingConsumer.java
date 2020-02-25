package com.jjcc.rabbitmq.routing.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费端
 * @author Jjcc
 * @version 1.0.0
 * @className RoutingConsumer.java
 * @createTime 2020年02月23日 20:29:00
 */
@Component
public class RoutingConsumer {

    @RabbitListener(queues = "#{queueA.name}")
    public void receiver1(String message) {
        receiver(1, message);
    }

    @RabbitListener(queues = "#{queueB.name}")
    public void receiver2(String message) {
        receiver(2, message);
    }

    private void receiver(int instance, String message) {
        System.out.println("instance " + instance + " [x] Received '" + message + "'");
    }
}
