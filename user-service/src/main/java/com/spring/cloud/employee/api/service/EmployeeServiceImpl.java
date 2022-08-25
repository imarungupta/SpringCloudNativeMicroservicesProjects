package com.spring.cloud.employee.api.service;

import com.spring.cloud.employee.api.DTO.Department;
import com.spring.cloud.employee.api.DTO.ResponseTemplateDTO;
import com.spring.cloud.employee.api.entity.Employee;
import com.spring.cloud.employee.api.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public ResponseTemplateDTO getEmpWithDepartment(Long empId) {
        ResponseTemplateDTO dto = new ResponseTemplateDTO();

        Employee employee = repository.findById(empId).get();

        // Calling the department-service from employee-service using restTemplate
        // Syntax: restTemplate.getForObject(url, Entity.class)

        //Department department = restTemplate.getForObject("http://localhost:9001/departments/" + empployee.getDepartmentId(), Department.class);

        Department department = restTemplate.getForObject
                ("http://DEPARTMENT-SERVICE/departments/" + employee.getDepartmentId(), Department.class);
        // DEPARTMENT-SERVICE : we have replaced this with localhost:9001 after registered with Eureka server

        dto.setEmployee(employee);
        dto.setDepartment(department);
        return dto;
    }
}
