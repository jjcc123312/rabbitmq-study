package com.jjcc.rabbitmq;

import com.jjcc.rabbitmq.basicdemo.produce.Demo01Producer;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @version 1.0.0
 * @description
 * @className Demo01ProducerTest.java
 * @createTime 2020年02月19日 21:26:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqBasicDemoApplication.class)
@Log4j2
public class Demo01ProducerTest {

    @Autowired
    private Demo01Producer producer;

    @Test
    public void testSyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        for (int i = 0; i < 10; i++) {

            producer.syncSend(id);
            log.info("[testSyncSend][发送编号：[{}] 发送成功]", id);
        }


        // 阻塞当前线程，直到CountDownLatch归0或超时
        new CountDownLatch(1).await();
    }

    @Test
    public void testSyncSendDefault() throws InterruptedException {
        int id =  (int) (System.currentTimeMillis() / 1000);
        producer.syncSendDefault(id);

        log.info("[tesSyncSendDefault][发送编号：[{}] 发送成功]", id);

        // 阻塞当前线程，直到CountDownLatch归0或超时
        new CountDownLatch(1).await(5, TimeUnit.SECONDS);
    }

    @Test
    public void testAsyncSend() throws InterruptedException {
        int id =  (int) (System.currentTimeMillis() / 1000);
        producer.asyncSend(id).addCallback(new ListenableFutureCallback<Void>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("[testASyncSend][发送编号：[{}] 发送异常]]", id, ex);

            }

            @Override
            public void onSuccess(Void result) {
                log.info("[testASyncSend][发送编号：[{}] 发送成功，发送成功]", id);
            }
        });
        log.info("[testASyncSend][发送编号：[{}] 调用完成]", id);

        // 阻塞当前线程，直到CountDownLatch归0或超时
        new CountDownLatch(1).await(5, TimeUnit.SECONDS);
    }

}






