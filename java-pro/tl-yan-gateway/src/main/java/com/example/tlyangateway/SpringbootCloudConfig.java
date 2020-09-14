package com.example.tlyangateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringbootCloudConfig {

	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes().route(r -> r.path("/customer/**").uri("http://127.0.0.1:8111/").id("customer"))

				.route(r -> r.path("/support/**").uri("http://127.0.0.1:8111/").id("support")).build();
	}

}