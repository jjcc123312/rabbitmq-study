package com.jjcc.rabbitmq.produce;

import com.jjcc.rabbitmq.message.Demo01Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * 生产者
 * @author Administrator
 * @version 1.0.0
 * @description
 * @className Demo01Producer.java
 * @createTime 2020年02月19日 16:56:00
 */
@Component
public class Demo01Producer {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public Demo01Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 调用 RabbitTemplate 的同步发送消息方法。
     * convertAndSend(...)：使用特定的路由键将其发送到特定的交换器。
     * @title syncSend
     * @author Jjcc
     * @param id 消息编号
     * @return void
     * @createTime 2020/2/19 0019 17:09
     */
    public void syncSend(Integer id) {
        // 创建 demo01Message 消息
        Demo01Message message = new Demo01Message();
        message.setId(id);
        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo01Message.EXCHANGE, Demo01Message.ROUTING_KEY, message);
    }

    /**
     * 调用 RabbitTemplate 的同步发送消息方法。
     * convertAndSend()：将Java对象转换为Amqp消息并将其发送到具有特定路由键的默认交换器。
     * @title syncSendDefault
     * @author Jjcc
     * @param id
     * @return void
     * @createTime 2020/2/19 0019 17:17
     */
    public void syncSendDefault(Integer id){
        // 创建 demo01Message 消息
        Demo01Message message = new Demo01Message();
        message.setId(id);
        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo01Message.QUEUE, message);
    }

    /**
     * 异步方法。用于调用发送消息方法
     * @title asyncSend
     * @author Jjcc
     * @param id
     * @return org.springframework.util.concurrent.ListenableFuture<java.lang.Void>
     * @createTime 2020/2/19 0019 17:19
     */
    @Async
    public ListenableFuture<Void> asyncSend(Integer id) {
        try {
            // 发送消息
            this.syncSend(id);
            // 返回成功的 Future
            return AsyncResult.forValue(null);
        } catch (Throwable ex) {
            // 返回异常的 Future
            return AsyncResult.forExecutionException(ex);
        }
    }

}
