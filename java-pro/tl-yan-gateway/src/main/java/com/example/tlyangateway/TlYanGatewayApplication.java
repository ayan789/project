package com.example.tlyangateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;



@SpringBootApplication
@EnableEurekaClient
@EnableAutoConfiguration
public class TlYanGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TlYanGatewayApplication.class, args);
	}

}
