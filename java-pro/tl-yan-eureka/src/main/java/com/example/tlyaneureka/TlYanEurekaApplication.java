package com.example.tlyaneureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class TlYanEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TlYanEurekaApplication.class, args);
	}

}
