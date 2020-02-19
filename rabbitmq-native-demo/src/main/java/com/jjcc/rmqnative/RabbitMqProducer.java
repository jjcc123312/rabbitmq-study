package com.jjcc.rmqnative;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rabbitmq生产者
 * Hello world!
 *
 * @author Administrator
 */
public class RabbitMqProducer {

    private static final String IP_ADDRESS = "127.0.0.1";

    private static final Integer PORT = 5672;

    private static final String USERNAME = "guest";

    private static final String PASSWORD = "guest";

    private static final String EXCHANGE_NAME = "exchange_demo";

    private static final String ROUTING_KEY = "routingkey_demo";

    /**
     * 只有 QUEUE_NAME 需要共享给 RabbitMQConsumer
     */
    private static final String QUEUE_NAME = "queue_demo";

    public static void main( String[] args ) throws IOException, TimeoutException {
        // 建立rabbitmiq连接
        Connection connection = getConnection();

        // 创建信道
        Channel channel = connection.createChannel();

        // 初始化 exchange 与 queue
        initExchangeAndQueue(channel);

        // 发 cycle 次送消息
        int cycle = 3;
        for (int i = 0; i < cycle; i++) {
            String message = "Hello World!!!" + i + "。";
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        }

        // 关闭
        channel.close();
        connection.close();
    }


    /**
     * 创建rabbitmq连接
     * @title getConnection
     * @author Jjcc
     * @return com.rabbitmq.client.Connection
     * @createTime 2020/2/17 0017 22:21
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        return factory.newConnection();
    }

    /**
     * 创建 Exchange 交换器、queue 消息队列，然后使用 ROUTING_KEY 路由键将两者绑定。
     * 该步骤，其实可以在 RabbitMQ Management 上操作，并不一定需要在代码中
     * @title initExchangeAndQueue
     * @author Jjcc
     * @param channel rabbitmq的信道
     * @return void
     * @createTime 2020/2/17 0017 22:40
     */
    private static void initExchangeAndQueue(Channel channel) throws IOException {
        // 创建交换器：direct、持久化、不自动删除。
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);

        // 创建消息队列：持久性、非排他、非自动删除的队列。
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        // 将交换器与队列通过路由器ID绑定。
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
    }

}
