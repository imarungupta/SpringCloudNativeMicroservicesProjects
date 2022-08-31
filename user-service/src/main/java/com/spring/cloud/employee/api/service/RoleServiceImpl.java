package com.spring.cloud.employee.api.service;

import com.spring.cloud.employee.api.entity.Employee;
import com.spring.cloud.employee.api.entity.Role;
import com.spring.cloud.employee.api.repository.EmployeeRepository;
import com.spring.cloud.employee.api.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Let's call this method using @PostConstruct,so that it could be called during service startup and can insert Admin and user
    // Since we are using the postman to insert User detail so will not call this method at all.
    /*public void insertAdminAndUserRole(){
        Role adminRole =new Role();
        adminRole.setRole("Admin");
        adminRole.setDescription("Admin Role");
        roleRepository.save(adminRole);

        Role userRole =new Role();
        userRole.setRole("User");
        userRole.setDescription("User Role");
        roleRepository.save(userRole);

        Employee adminEmployee= new Employee();
        adminEmployee.setFirstName("Arun");
        adminEmployee.setLastName("Gupta");
        adminEmployee.setEmail("arun@gmail.com");

        List<Role> adminRoles= new ArrayList<>();
        adminRoles.add(adminRole);
        adminEmployee.setRole(adminRoles);
        employeeRepository.save(adminEmployee);

        Employee userEmployee= new Employee();
        userEmployee.setFirstName("Tarun");
        userEmployee.setLastName("Gupta");
        userEmployee.setEmail("tarun@gmail.com");

        List<Role> userRoles= new ArrayList<>();
        userRoles.add(userRole);
        userEmployee.setRole(userRoles);
        employeeRepository.save(userEmployee);

    }*/
}
