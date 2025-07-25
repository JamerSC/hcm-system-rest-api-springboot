package com.jamersc.springboot.hcm_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeePatchDTO {
    private Long id;
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;
    //@NotBlank(message = "Job Position is required")
    private String jobPosition;
    //@NotBlank(message = "Department is required")
    private String department;
    @NotNull(message = "Hired date is required")
    private LocalDate hiredDate;
    @NotNull(message = "Salary is required")
    @Min(value = 0, message = "Salary must be a non-negative value")
    private Double salary;
}
