package com.jjcc.rabbitmq.publishsubscribe.message;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息类。
 * @author Administrator
 * @version 1.0.0
 * @className Message.java
 * @createTime 2020年02月21日 16:47:00
 */
@Data
public class Message implements Serializable {

    public static final String QUEUE_A = "QUEUE_PS_A";

    public static final String QUEUE_B = "QUEUE_PS_B";

    public static final String EXCHANGE = "EXCHANGE_PS";

    /**
     * 编号
     */
    private Integer id;

    /**
     * 消息主题
     * @title
     * @author Jjcc
     * @return
     * @createTime 2020/2/21 0021 16:51
     */
    private String body;

}
