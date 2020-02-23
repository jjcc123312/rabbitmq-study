package com.jjcc.rabbitmq;

import com.jjcc.rabbitmq.publishsubscribe.message.Message;
import com.jjcc.rabbitmq.publishsubscribe.producer.PsProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * @author Jjcc
 * @version 1.0.0
 * @className PublishSubscribePatternTest.java
 * @createTime 2020年02月21日 22:40:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqBasicDemoApplication.class)
public class PublishSubscribePatternTest {

    @Autowired
    private PsProducer producer;

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Message message = new Message();
            message.setId((int) (System.currentTimeMillis() / 100));
            message.setBody("hello world：" + i);
            producer.asyncSend(message);
        }

        new CountDownLatch(1).await();
    }
}




