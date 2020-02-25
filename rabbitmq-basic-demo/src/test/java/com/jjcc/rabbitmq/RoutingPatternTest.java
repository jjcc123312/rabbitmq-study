package com.jjcc.rabbitmq;

import com.jjcc.rabbitmq.routing.producer.RoutingProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * @author Jjcc
 * @version 1.0.0
 * @className RoutingPatternTest.java
 * @createTime 2020年02月24日 12:56:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqBasicDemoApplication.class)
public class RoutingPatternTest {

    @Autowired
    private RoutingProducer producer;

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            producer.send();
        }

        new CountDownLatch(1).await();
    }
}
