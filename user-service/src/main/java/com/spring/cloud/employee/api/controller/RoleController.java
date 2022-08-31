package com.spring.cloud.employee.api.controller;

import com.spring.cloud.employee.api.entity.Role;
import com.spring.cloud.employee.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

/*@RestController
@RequestMapping("/role")*/
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/")
    public Role saveRole(@RequestBody Role role){

        return roleService.saveRole(role);
    }
    @GetMapping("/")
    public List<Role> getAllRoles(){

        return roleService.getAllRoles();
    }
// The Below method will be called during the application startup and will insert the Admin and User.
// Since we are inserting via postman so let's comment the below code

 /* @PostConstruct
    public void insertAdminAndUser(){
        roleService.insertAdminAndUserRole();
    }*/
}
