package com.jjcc.rabbitmq.consumer;

import com.jjcc.rabbitmq.message.Demo01Message;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费端。
 * @RabbitListener 根据队列名监听指定的消息队列，是一个数组。
 * @author Administrator
 * @version 1.0.0
 * @description
 * @className Demo01Consumer.java
 * @createTime 2020年02月19日 21:07:00
 */
@Log4j2
@Component
@RabbitListener(queues = Demo01Message.QUEUE)
public class Demo01Consumer {

    /**
     * 通过 @RabbitHandler 注解申明了处理消息的方法，
     * @title onMessage
     * @author Jjcc
     * @param message 方法入参为消息的类型。
     * @return void
     * @createTime 2020/2/19 0019 21:15
     */
    @RabbitHandler
    public void  onMessage(Demo01Message message) {
        log.info("[onMessage][线程编号：{}，消息内容：{}]", message.getId(), message.toString());
    }

    /**
     * 获得消费消息的更多信息，例如说，RoutingKey、创建时间等等信息
     * @title onMessage
     * @author Jjcc
     * @param message 消息对象。
     * @return void
     * @createTime 2020/2/19 0019 21:17
     */
//    @RabbitHandler(isDefault = true)
//    public void onMessage(Message message) {
//        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
//    }
}
