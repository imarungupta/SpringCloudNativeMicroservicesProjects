package com.spring.cloud.employee.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long empId;
    private String firstName;
    private String lastName;
    private String email;
    private Long departmentId;
    private Long roleId;
    private String empPassword;

    //private List<Long> roleId;

/*    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "EMP_ROLE_TBL",
            joinColumns = {
                    @JoinColumn(name = "emp_id", referencedColumnName = "empId")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "roleId")
            }
    )
    private Set<Role> role;
    // Many employee can have set of roles*/
}

/*
@JoinTable(name = "EMP_ROLE_TBL",
        joinColumns = @JoinColumn(name = "emp_id", referencedColumnName = "empId"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleId")
)*/
