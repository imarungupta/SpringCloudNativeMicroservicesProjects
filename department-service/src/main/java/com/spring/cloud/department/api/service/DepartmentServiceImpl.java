package com.spring.cloud.department.api.service;

import com.spring.cloud.department.api.entity.Department;
import com.spring.cloud.department.api.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository repository;
    @Override
    public Department saveDepartment(Department department) {
        log.info("Inside the saveDepartment of service impl");
        return repository.save(department);
    }
    @Override
    public Department findDepartmentById(Long departmentId) {
        log.info("Inside the findDepartmentById of service impl");
        return repository.findById(departmentId).get();
    }
}
