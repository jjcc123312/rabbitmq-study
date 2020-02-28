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
    public void contextLoads() throws InterruptedException {
       sendDelay(null, null);
    }

    @Test
    public void contextLoadsB() throws InterruptedException {
        sendDelay(10000, "延迟10秒");
        sendDelay(5000, "延迟5秒");
        new CountDownLatch(1).await();
    }


    public void sendDelay(Integer delay, String para) throws InterruptedException {
        producer.send(delay, para);
        System.out.println("消息发送完成！！！！！！！！！");

    }

}
