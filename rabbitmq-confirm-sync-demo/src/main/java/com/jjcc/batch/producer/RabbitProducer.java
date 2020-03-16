package com.jjcc.batch.producer;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    /**
     * 同步 Confirm 模式，消息的发送确认。
     * @title send
     * @author Jjcc
     * @return void
     * @createTime 2020/2/28 0028 17:36
     */
    public void send(Integer id) {

        /*
         * 参数一：OperationsCallback；自定义操作。
         * 参数二：confirmCallback；定义接收到 RabbitMQ Broker 的成功“响应”时的回调。
         * 参数三：confirmCallback；定义接收到 RabbitMQ Broker 的失败“响应”时的回调。
         */
        rabbitTemplate.invoke( (operations) -> {
            // 同步发送消息
            operations.convertAndSend("exchange_concurrency", "concurrency.key", id);
            log.info("[doInRabbit][发送消息完成]");
            // 等待确认；本质是使当前线程进入等待阻塞，直到回执唤醒
            // timeout 参数为0，表示无限等待
            operations.waitForConfirms(0);
            log.info("[doInRabbit][等待 Confirm 完成]");
            return null;
        }, (deliveryTag, multiple) ->
            // 消息发送成功
            log.info("[handle][Confirm 成功]")
        , (deliveryTag, multiple) ->
            // 消息发送失败
            log.info("[handle][Confirm 失败]")
        );

    }
}
