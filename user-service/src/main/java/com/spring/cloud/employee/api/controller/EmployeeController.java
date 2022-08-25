package com.spring.cloud.employee.api.controller;

import com.spring.cloud.employee.api.entity.Employee;
import com.spring.cloud.employee.api.DTO.ResponseTemplateDTO;
import com.spring.cloud.employee.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping("/")
    public Employee saveEmployee(@RequestBody Employee employee){

        Employee savedEmp = service.saveEmployee(employee);
        return savedEmp;
    }

    @GetMapping("/{id}")
    public ResponseTemplateDTO getEmpWithDepartment(@PathVariable("id") Long empId){

        ResponseTemplateDTO empWithDepartment = service.getEmpWithDepartment(empId);
        return empWithDepartment;
    }
}
