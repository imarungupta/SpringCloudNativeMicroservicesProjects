package com.spring.cloud.department.api.controller;

import com.spring.cloud.department.api.entity.Department;
import com.spring.cloud.department.api.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {
    @Autowired
    private DepartmentService service;

    @PostMapping("/")
    public Department saveDepartment(@RequestBody Department department){
        log.info("Inside the saveDepartment of Controller");
        return service.saveDepartment(department);
    }
    @GetMapping("/{id}")
    public Department findDepartmentById(@PathVariable("id") Long departmentId){
        log.info("Inside the findDepartmentById of Controller");
        return service.findDepartmentById((long) Math.toIntExact(departmentId));
    }
}
