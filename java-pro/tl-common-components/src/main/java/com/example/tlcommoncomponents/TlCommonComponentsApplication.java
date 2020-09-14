package com.example.tlcommoncomponents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@AutoConfigurationPackage
@ComponentScan(basePackages = {"com.example"})
public class TlCommonComponentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TlCommonComponentsApplication.class, args);
	}

}
