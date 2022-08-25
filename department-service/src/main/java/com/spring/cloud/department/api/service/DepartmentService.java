package com.spring.cloud.department.api.service;

import com.spring.cloud.department.api.entity.Department;

public interface DepartmentService {
    Department saveDepartment(Department department);
    Department findDepartmentById(Long departmentId);
}
