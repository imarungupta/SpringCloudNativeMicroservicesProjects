package com.spring.cloud.cloud.api.gateway;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableHystrix
@EnableEurekaClient
@SpringBootApplication
public class CloudApiGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(CloudApiGatewayApplication.class, args);
	}
}
