package com.jjcc.rabbitmq.workqueuer.consumer;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * 消费者
 * @author Administrator
 * @version 1.0.0
 * @description
 * @className Demo02Consumer.java
 * @createTime 2020年02月20日 14:47:00
 */
@Log4j2
@RabbitListener(queues = "queue_demo01", containerFactory = "prefetchOneRabbitListenerContainerFactory")
public class Demo02Consumer {

    private int instance;

    public Demo02Consumer(int instance) {
        this.instance = instance;
    }

    /**
     * 消费者。
     * @title receive
     * @author Jjcc
     * @param in 编号
     * @return void
     * @createTime 2020/2/20 0020 14:54
     */
    @RabbitHandler
    public void receive(String in) throws InterruptedException {
        log.info("[onMessage][编号：{}，消息内容：{}]", this.instance, in);
        doWork(in);
    }

    private void doWork(String in) throws InterruptedException {
        char[] chars = in.toCharArray();
        for (char aChar : chars) {
           if ('.' == aChar) {
               Thread.sleep(1000);
           }
        }
    }

}
