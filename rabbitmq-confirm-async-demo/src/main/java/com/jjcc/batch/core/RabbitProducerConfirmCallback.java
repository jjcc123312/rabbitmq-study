package com.jjcc.batch.core;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 异步的 confirm 的回执类
 * @author Jjcc
 * @version 1.0.0
 * @className RabbitProducerConfirmCallback.java
 * @createTime 2020年03月16日 22:05:00
 */
@Component
@Log4j2
public class RabbitProducerConfirmCallback implements RabbitTemplate.ConfirmCallback {

    /**
     * 把自己设置到 RabbitTemplate 中，作为 Confirm 的回调。
     * @title RabbitProducerConfirmCallback
     * @author Jjcc
     * @param rabbitTemplate amqp提供的模板类
     * @createTime 2020/3/16 22:10
     */
//    public RabbitProducerConfirmCallback(RabbitTemplate rabbitTemplate) {
//        rabbitTemplate.setConfirmCallback(this);
//    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        // ack 为 true，消息成功转发到 queue中；为 false，消息转发到 queue 失败
        if (ack) {
            log.info("[confirm][Confirm 成功 correlationData: {}]", correlationData);
        } else {
            log.error("[confirm][Confirm 失败 correlationData: {} cause: {}]", correlationData, cause);
        }
    }
}
