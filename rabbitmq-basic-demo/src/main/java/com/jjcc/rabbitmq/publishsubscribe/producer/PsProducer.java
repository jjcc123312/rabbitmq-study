package com.jjcc.rabbitmq.publishsubscribe.producer;

import com.jjcc.rabbitmq.publishsubscribe.message.Message;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 生产者
 * @author Administrator
 * @version 1.0.0
 * @description
 * @className PsProducer.java
 * @createTime 2020年02月21日 16:48:00
 */
@Log4j2
@Component
public class PsProducer {

    private RabbitTemplate template;

    private AtomicLong dost = new AtomicLong(0);
    private AtomicLong count = new AtomicLong(0);

    @Autowired
    public PsProducer(RabbitTemplate template) {
        this.template = template;
    }

    /**
     * 消息发送者。
     * 这里发送消息的 convertAndSend方法的 routing key 参数设置为""，因为 fanoutExchange不需要。
     * @title asyncSend
     * @author Jjcc
     * @param message 消息体
     * @return void
     * @createTime 2020/2/21 0021 23:09
     */
    @Async
    public void asyncSend(Message message) {

        if (dost.incrementAndGet() == 3) {
            dost.set(1);
        }

        StringBuilder sb = new StringBuilder();
        for (long i = 0; i < dost.get(); i++) {
            sb.append(".");
        }
        message.setBody(message.getBody() + sb.toString() + "-----" + count.incrementAndGet());

        template.convertAndSend(Message.EXCHANGE, "", message);

        log.info(" [x] Sent '" + message.toString() + "'");
    }
}



