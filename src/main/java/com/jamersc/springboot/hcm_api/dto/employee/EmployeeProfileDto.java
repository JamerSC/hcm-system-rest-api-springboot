package com.jamersc.springboot.hcm_api.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeProfileDto {
    // Fields from Employee entity
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String jobPosition;
    private LocalDate hiredDate;
    private Double salary;
    // Fields from User entity
    private String username;
    private Set<String> roles;
}
