package com.jjcc.batch.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 消费端
 * @author Jjcc
 * @version 1.0.0
 * @className RabbitConsumer.java
 * @createTime 2020年02月26日 13:43:00
 */
@Component
@Log4j2
public class RabbitConsumer {


    /**
     * 默认情况下,如果没有配置手动ACK, 那么Spring Data AMQP 会在消息消费完毕后自动帮我们去ACK
     * 存在问题：如果报错了,消息不会丢失,但是会无限循环消费,一直报错,如果开启了错误日志很容易就吧磁盘空间耗完
     * @title ackReceiver
     * @author Jjcc
     * @param id 传递的参数
     * @param channel 信道
     * @param message 消息传递的参数
     * @return void
     * @createTime 2020/3/15 22:52
     */
    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue(value = "queue_concurrency1", durable = "true", autoDelete = "false", exclusive = "false")
                    , exchange = @Exchange(name = "exchange_concurrency1", autoDelete = "true")
                    , key = "concurrency.key1")})
    public void ackReceiver(Integer id, Channel channel, Message message) throws IOException {
        // 通知 broker 消息已接收，可以 ACK(从队列中删除) 了
        // 第二个参数 multiple ，用于批量确认消息，为了减少网络流量，手动确认可以被批处。
        // 1. 当 multiple 为 true 时，则可以一次性确认 deliveryTag 小于等于传入值的所有消息
        // 2. 当 multiple 为 false 时，则只确认当前 deliveryTag 对应的消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        try {
            log.info("[消息接收者：{}][消息主题：{}]", Thread.currentThread().getId(), "消息id：" + id);
        } catch (Exception e) {
            /*
             * basicRecover方法是进行补发操作；
             * 参数为true，是把消息退回到queue，但是有可能被其它的consumer(集群)接收到。
             * 参数为false，把消息退回到queue，只补发给当前的consumer。
             */
            channel.basicRecover(false);

            e.printStackTrace();
        }
    }
}
