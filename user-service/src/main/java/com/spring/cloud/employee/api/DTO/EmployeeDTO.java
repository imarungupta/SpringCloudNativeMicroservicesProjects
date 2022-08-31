package com.spring.cloud.employee.api.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {

    private String firstName;
    private String lastName;
    private String email;
    private Long departmentId;
    private Long roleId;
    private String empPassword;
}
