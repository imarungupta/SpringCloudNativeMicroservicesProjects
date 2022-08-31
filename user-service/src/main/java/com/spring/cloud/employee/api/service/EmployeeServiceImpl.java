package com.spring.cloud.employee.api.service;

import com.spring.cloud.employee.api.DTO.Department;
import com.spring.cloud.employee.api.DTO.EmployeeDTO;
import com.spring.cloud.employee.api.DTO.ResponseTemplateDTO;
import com.spring.cloud.employee.api.entity.Employee;
import com.spring.cloud.employee.api.repository.EmployeeRepository;
import com.spring.cloud.employee.api.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void insertEmployee(){
        Employee adminEmployee= new Employee();

        adminEmployee.setFirstName("Ram");
        adminEmployee.setLastName("Kumar");
        adminEmployee.setEmail("ram@gmail.com");
        adminEmployee.setDepartmentId(1L);
        adminEmployee.setRoleId(1l);
        adminEmployee.setEmpPassword(passwordEncoder.encode("admin123"));
        repository.save(adminEmployee);

        Employee userEmployee= new Employee();
        userEmployee.setFirstName("Kishan");
        userEmployee.setLastName("Kumar");
        userEmployee.setEmail("kishan@gmail.com");
        userEmployee.setDepartmentId(1L);
        userEmployee.setRoleId(1l);
        userEmployee.setEmpPassword(passwordEncoder.encode("user123"));
        repository.save(userEmployee);
    }

    @Override
    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = Employee.builder()
                .firstName(employeeDTO.getFirstName())
                .lastName(employeeDTO.getLastName())
                .email(employeeDTO.getEmail())
                .departmentId(employeeDTO.getDepartmentId())
                .roleId(employeeDTO.getRoleId()).build();
            employee.setEmpPassword(passwordEncoder.encode(employeeDTO.getEmpPassword()));

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
        //roleRepository.findROleNameByRoleId(employee.getRoleId());
        dto.setEmployee(employee);
        dto.setDepartment(department);
        dto.setRole(roleRepository.findROleByRoleId(employee.getRoleId()));
        return dto;
    }
}
