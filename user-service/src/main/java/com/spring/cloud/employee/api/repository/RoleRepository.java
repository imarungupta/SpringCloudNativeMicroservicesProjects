package com.spring.cloud.employee.api.repository;

import com.spring.cloud.employee.api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findROleByRoleId(Long roleId);
}
