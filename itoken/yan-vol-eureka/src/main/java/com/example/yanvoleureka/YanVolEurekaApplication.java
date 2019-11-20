package com.example.yanvoleureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class YanVolEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(YanVolEurekaApplication.class, args);
    }

}
