package com.jjcc.rabbitmq.publishsubscribe.consumer;

import com.jjcc.rabbitmq.publishsubscribe.message.Message;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者。
 * 定义两个消费者分别监听两个队列
 * @author Administrator
 * @version 1.0.0
 * @description
 * @className PsConsumer.java
 * @createTime 2020年02月21日 16:47:00
 */
@Component
@Log4j2
public class PsConsumer {

    /**
     * @RabbitListener也可用于方法之上。
     * 注解参数使用 SpEL表达式 获取 已经注册进IOC容器的队列对象。
     * @title receiveA
     * @author Jjcc
     * @param message 消息体
     * @return void
     * @createTime 2020/2/21 0021 22:52
     */
    @RabbitListener(queues = "#{autoDeleteQueueA.name}")
    public void receiveA(Message message) {
       log.info("[onMessage][线程编号:{} 消息内容：{}]", "A", message.toString());
    }

    /**
     * @RabbitListener也可用于方法之上。
     * 注解参数使用 SpEL表达式 获取 已经注册进IOC容器的队列对象。
     * @title receiveB
     * @author Jjcc
     * @param message 消息体
     * @return void
     * @createTime 2020/2/21 0021 22:54
     */
    @RabbitListener(queues = "#{autoDeleteQueueB.name}")
    public void receiveB(Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", "B", message.toString());
    }
}
