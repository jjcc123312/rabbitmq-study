package com.jjcc.batch.core;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jjcc
 * @version 1.0.0
 * @className RabbitProducerReturnCallback.java
 * @createTime 2020年03月17日 12:39:00
 */
@Log4j2
@Component
public class RabbitProducerReturnCallback implements RabbitTemplate.ReturnCallback {

//    public RabbitProducerReturnCallback(RabbitTemplate rabbitTemplate) {
//        rabbitTemplate.setReturnCallback(this);
//    }

    /**
     * 消息通过 exchange 路由到 queue 时，没有找到对应的queue时回执的方法
     * @title returnedMessage
     * @author Jjcc
     * @param message 返回的消息体
     * @param replyCode 回复代码
     * @param replyText 回复文本
     * @param exchange 交换机name
     * @param routingKey 路由key
     * @createTime 2020/3/17 12:44
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("1111111111");
        log.info("[returnedMessage][message: [{}] replyCode: [{}] replyText: [{}] exchange: [{}] routingKey: [{}]]",
                message, replyCode, replyText, exchange, routingKey);
    }
}
