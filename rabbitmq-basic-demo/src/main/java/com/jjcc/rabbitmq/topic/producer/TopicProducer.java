package com.jjcc.rabbitmq.topic.producer;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Jjcc
 * @version 1.0.0
 * @className TopicProducer.java
 * @createTime 2020年02月25日 16:58:00
 */
@Component
public class TopicProducer {

    private RabbitTemplate template;

    private TopicExchange exchange;

    private AtomicLong index = new AtomicLong();

    private AtomicLong count = new AtomicLong();

    private final String[] KEYS = {"quick.orange.rabbit",
            "lazy.orange.elephant", "quick.orange.fox",
            "lazy.brown.fox", "lazy.pink.rabbit", "quick.brown.fox"};

    @Autowired
    public TopicProducer(RabbitTemplate template, TopicExchange exchange) {
        this.template = template;
        this.exchange = exchange;
    }

    @Async
    public void send() {
        StringBuilder builder = new StringBuilder("Hello to ");

        if (index.incrementAndGet() == KEYS.length) {
            index.set(0);
        }

        String key = KEYS[(int) this.index.get()];
        builder.append(key).append(' ');
        builder.append(this.count.incrementAndGet());
        String message = builder.toString();

        template.convertAndSend(exchange.getName(), key, message);

        System.out.println(" [x] Sent '" + message + "'");
    }
}
