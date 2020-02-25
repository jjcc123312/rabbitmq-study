package com.jjcc.rabbitmq.topic.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Jjcc
 * @version 1.0.0
 * @className Topic.java
 * @createTime 2020年02月25日 16:58:00
 */
@Component
public class TopicConsumer {

    @RabbitListener(queues = "#{queueTopicA.name}")
    public void receiver1(String message) {
        receiver("queueTopicA", message);
    }

    @RabbitListener(queues = "#{queueTopicB.name}")
    public void receiver2(String message) {
        receiver("queueTopicB", message);
    }

    private void receiver(String instance, String message) {
        System.out.println("队列名称： " + instance + " [x] Received '" + message + "'");
    }

}
