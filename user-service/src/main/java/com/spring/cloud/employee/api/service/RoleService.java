package com.spring.cloud.employee.api.service;

import com.spring.cloud.employee.api.entity.Role;

import java.util.List;

public interface RoleService {
    Role saveRole(Role role);

    List<Role> getAllRoles();

    //public void insertAdminAndUserRole();
}
