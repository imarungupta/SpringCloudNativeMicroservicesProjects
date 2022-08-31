package com.spring.cloud.employee.api.DTO;

import com.spring.cloud.employee.api.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequestDTO {

    private String username;
    private String password;

}
