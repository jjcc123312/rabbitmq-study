package com.jjcc.rabbitmq.routing.producer;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 生产者
 * @author Jjcc
 * @version 1.0.0
 * @className RoutingProducer.java
 * @createTime 2020年02月23日 20:29:00
 */
@Component
public class RoutingProducer {

    private RabbitTemplate template;

    private DirectExchange exchange;

    private String[] keys = {"error", "info", "warning"};

    private AtomicLong index = new AtomicLong();

    private AtomicLong count = new AtomicLong();

    @Autowired
    public RoutingProducer(RabbitTemplate template, DirectExchange directExchange) {
        this.template = template;
        this.exchange = directExchange;
    }


    @Async
    public void send() {
        StringBuilder builder = new StringBuilder("Hello to ");

        if (index.incrementAndGet() == 3) {
            this.index.set(0);
        }
        String key = keys[(int) index.get()];
        builder.append(key).append(' ').append(this.count.incrementAndGet());
        String message = builder.toString();

        template.convertAndSend(exchange.getName(), key, message);
        System.out.println(" [x] Sent '" + message + "'");
    }

}





