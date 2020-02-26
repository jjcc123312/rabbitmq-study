package com.jjcc.batch;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @EnableRabbit：开启基于注解的rabbitmq
 * @author Administrator
 */
@EnableRabbit
@EnableAsync
@SpringBootApplication
public class RabbitmqBatchDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqBatchDemoApplication.class, args);
    }

}
