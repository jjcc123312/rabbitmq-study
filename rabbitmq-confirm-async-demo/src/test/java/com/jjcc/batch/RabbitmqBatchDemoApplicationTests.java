package com.jjcc.batch;

import com.jjcc.batch.producer.RabbitProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@SpringBootTest(classes = RabbitmqBatchDemoApplication.class)
@RunWith(SpringRunner.class)
public class RabbitmqBatchDemoApplicationTests {

    @Autowired
    private RabbitProducer producer;


    @Test
    public void contextLoadsB() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            sendDelay(i);
        }
        System.out.println("消息发送完成！！！！！！！！！");
        new CountDownLatch(1).await();
    }

    @Test
    public void contextLoadsC() throws InterruptedException {
        sendReturn(1);
        System.out.println("消息发送完成！！！！！！！！！");
        new CountDownLatch(1).await();
    }


    public void sendDelay(Integer para) {
        producer.send(para);

    }

    public void sendReturn(Integer para) {
        producer.sendReturn(para);

    }

}
