package com.jjcc.batch.producer;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Jjcc
 * @version 1.0.0
 * @className RabbitProducer.java
 * @createTime 2020年02月26日 14:24:00
 */
@Component
@Log4j2
public class RabbitProducer {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private AtomicLong count = new AtomicLong();

    /**
     * 发送消息时，相比于前面的生产者，这里额外添加了一个 org.springframework.amqp.core.MessagePostProcessor 参数。
     * MessagePostProcessor 是一个接口，提供一个 postProcessMessage()方法，可以通过该方法指定该条消息的存活时间。
     * @title send
     * @author Jjcc
     * @param delay 消息过期时间
     * @return void
     * @createTime 2020/2/28 0028 17:36
     */
//    @Async
    public void send(Integer delay, String para) {
        String s = "Hello World!!!："  + para;
        rabbitTemplate.convertAndSend("delay_plugins_exchange", "delay_key", s, (message) -> {

            Optional<Integer> delayOpt = Optional.ofNullable(delay);

            // 如果 delay 参数不为空，则设置消息的存活时间。
            // 这里是通过 plugins 方式实现的延迟队列，需要调用 setHeader("x-delay", delay); 而不是 setExpiration(String.valueOf(delay));
            delayOpt.ifPresent( c -> {
                if (0 != c) {
//                    message.getMessageProperties().setExpiration(String.valueOf(delay));
                    message.getMessageProperties().setHeader("x-delay", delay);
                }
            });
            return message;
        });
        log.info("消息发送完成！！！！！！！！！");
    }
}
