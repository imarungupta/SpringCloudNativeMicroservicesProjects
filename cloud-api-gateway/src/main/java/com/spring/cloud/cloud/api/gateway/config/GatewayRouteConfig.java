package com.spring.cloud.cloud.api.gateway.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRouteConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder routeLocatorBuilder) {

        return routeLocatorBuilder.routes()
                .route(
                        filter -> filter
                                .path("Path=/employees/**")
                                .uri("lb://EMPLOYEE-SERVICE")
                      )
                .route(
                        filter -> filter
                                .path("/departments/**")
                                .uri("lb://DEPARTMENT-SERVICE")
                      )
                .build();
    }
}
