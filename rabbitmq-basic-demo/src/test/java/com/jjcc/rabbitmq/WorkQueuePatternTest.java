package com.jjcc.rabbitmq;

import com.jjcc.rabbitmq.workqueuer.producer.Demo02Produce;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * @author Administrator
 * @version 1.0.0
 * @description
 * @className WorkQueuePatternTest.java
 * @createTime 2020年02月20日 15:05:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqBasicDemoApplication.class)
public class WorkQueuePatternTest {

    @Autowired
    private Demo02Produce produce;

    @Test
    public void testSend() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            produce.send();
        }
        new CountDownLatch(1).await();
    }
}
