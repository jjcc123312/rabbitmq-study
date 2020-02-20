package com.jjcc.rabbitmq.message;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息类里，我们枚举了 Exchange、Queue、RoutingKey 的名字。
 * @author Administrator
 * @version 1.0.0
 * @description
 * @className Demo01Message.java
 * @createTime 2020年02月19日 15:14:00
 */
@Data
public class Demo01Message implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_01";

    public static final String EXCHANGE = "EXCHANGE_DEMO_01";

    public static final String ROUTING_KEY = "ROUTING_KEY_01";

    /**
     * 编号
     */
    private Integer id;

}
