package com.spring.cloud.employee.api.configuration;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Step-2 Implement the AuthenticationEntryPoint and override its commence method and set the response for unauthorized user

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        // First let's set the response when any unauthorized user trying to access the resource
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Un-authorized");

    }
}
