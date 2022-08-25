package com.spring.cloud.department.api.repository;

import com.spring.cloud.department.api.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    //Department findDepartmentById(Long departmentId);
}
