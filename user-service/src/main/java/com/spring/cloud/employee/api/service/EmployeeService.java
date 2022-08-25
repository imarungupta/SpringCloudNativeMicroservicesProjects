package com.spring.cloud.employee.api.service;

import com.spring.cloud.employee.api.DTO.ResponseTemplateDTO;
import com.spring.cloud.employee.api.entity.Employee;

public interface EmployeeService {

   // ResponseTemplateDTO getUserWithDepartment(Long empId);
    Employee saveEmployee(Employee employee);

    ResponseTemplateDTO getEmpWithDepartment(Long empId);
}
