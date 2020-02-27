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
    public void contextLoads01() throws InterruptedException {
        this.testSyncSend(3);
    }

    @Test
    public void contextLoads02() throws InterruptedException {
        this.testSyncSend(10);
    }

    public void testSyncSend(int n) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            producer.send();
            System.out.println("消息发送完成！！！！！！！！！");
        }

        new CountDownLatch(1).await();
    }

}
