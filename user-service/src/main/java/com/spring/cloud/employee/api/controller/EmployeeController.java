package com.spring.cloud.employee.api.controller;

import com.spring.cloud.employee.api.DTO.EmployeeDTO;
import com.spring.cloud.employee.api.entity.Employee;
import com.spring.cloud.employee.api.DTO.ResponseTemplateDTO;
import com.spring.cloud.employee.api.entity.Role;
import com.spring.cloud.employee.api.service.EmployeeService;
import com.spring.cloud.employee.api.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    Logger log= LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/")
    public Employee saveEmployee(@RequestBody EmployeeDTO employeeDTO){

        log.info("Inside the saveEmp Controller method");
        Employee savedEmp = employeeService.saveEmployee(employeeDTO);
        return savedEmp;
    }

    @GetMapping("/{id}")
    public ResponseTemplateDTO getEmpWithDepartment(@PathVariable("id") Long empId){

        ResponseTemplateDTO empWithDepartment = employeeService.getEmpWithDepartment(empId);
        return empWithDepartment;
    }
    //----------------------------------------------------------------------------------

    @Autowired
    private RoleService roleService;

    @PostMapping("/role")
    public Role saveRole(@RequestBody Role role){

        return roleService.saveRole(role);
    }
    @GetMapping("/role")
    public List<Role> getAllRoles(){

        return roleService.getAllRoles();
    }
// The Below method will be called during the application startup and will insert the Admin and User.
// Since we are inserting via postman so let's comment the below code

    /*@PostConstruct
    public void insertAdminAndUser(){
       // roleService.insertAdminAndUserRole();
        log.info("Start insert employee");
        employeeService.insertEmployee();
        log.info("End insert employee");
    }*/
}
