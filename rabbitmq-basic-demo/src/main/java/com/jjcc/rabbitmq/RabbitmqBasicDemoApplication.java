package com.jjcc.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @EnableAsync 开启spring boot异步支持
 * @author Administrator
 */
@EnableAsync
@SpringBootApplication
public class RabbitmqBasicDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqBasicDemoApplication.class, args);
    }

}
