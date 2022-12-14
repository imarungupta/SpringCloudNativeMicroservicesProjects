package com.spring.cloud.employee.api.DTO;


import com.spring.cloud.employee.api.entity.Employee;
import com.spring.cloud.employee.api.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateDTO {
    private Employee employee;
    private Department department;
    private Role role;
}
