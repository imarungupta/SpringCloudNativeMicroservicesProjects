package com.spring.cloud.employee.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class EmployeeServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(EmployeeServiceApplication.class, args);
    }
    @Bean
    @LoadBalanced // Use this annotation because this restTemplate is calling the service registered with Eureka so load has to be balanced
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }
}
