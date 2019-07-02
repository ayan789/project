package com.example.yan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class JasperReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(JasperReportApplication.class, args);
    }

}
