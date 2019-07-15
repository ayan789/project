package com.example.tlyannoif;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class TlYanNoifApplication {

    public static void main(String[] args) {
        SpringApplication.run(TlYanNoifApplication.class, args);
    }

}
