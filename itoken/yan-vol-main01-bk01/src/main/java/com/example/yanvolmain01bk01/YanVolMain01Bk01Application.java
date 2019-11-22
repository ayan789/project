package com.example.yanvolmain01bk01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class YanVolMain01Bk01Application {

    public static void main(String[] args) {
        SpringApplication.run(YanVolMain01Bk01Application.class, args);
    }

}
