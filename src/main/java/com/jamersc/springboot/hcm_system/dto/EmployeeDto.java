package com.jamersc.springboot.hcm_system.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String jobPosition;
    private String department;
    private LocalDate hireDate;
    private Double salary;
}
