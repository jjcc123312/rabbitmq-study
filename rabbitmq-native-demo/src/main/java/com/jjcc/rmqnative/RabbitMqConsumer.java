package com.jjcc.rmqnative;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author Administrator
 * @version 1.0.0
 * @description rabbitmq消费者
 * @className RabbitMqConsumer.java
 * @createTime 2020年02月17日 22:59:00
 */
public class RabbitMqConsumer {

    /**
     * 消息队列Id。
     */
    private static final String QUEUE_NAME = "queue_demo";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 建立连接。
        Connection connection = RabbitMqProducer.getConnection();

        // 创建信道
        Channel channel = connection.createChannel();

        // 设置客户端最多接收未被 ack 的消息数量为65
        channel.basicQos(65);

        // 创建消费者
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // 打印日志
                System.out.println(String.format("[线程：%s][路由键：%s][消息内容：%s]",
                        Thread.currentThread(), envelope.getRoutingKey(), new String(body)));

                // ack 消息已经消费
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        // 订阅 QUEUE_NAME 队列的消息。
        channel.basicConsume(QUEUE_NAME, consumer);

        // 关闭
        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException ignore) {
        }
        channel.close();
        connection.close();
    }
}
