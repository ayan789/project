package com.example.tlyancustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class TlYanCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TlYanCustomerApplication.class, args);
	}

}
