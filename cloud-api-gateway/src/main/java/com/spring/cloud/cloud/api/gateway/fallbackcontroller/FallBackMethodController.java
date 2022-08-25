package com.spring.cloud.cloud.api.gateway.fallbackcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {
    @GetMapping("/employeeServiceFallBack")
    public String employeeServiceFallBackMethod(){
        return "User/ Emp service is taking longer than expected."+
                "Please try again later";
    }
    @GetMapping("/departmentServiceFallBack")
    public String departmentServiceFallBackMethod(){
        return "Department service is taking longer than expected."+
                "Please try again later";
    }
}
