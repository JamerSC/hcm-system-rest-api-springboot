package com.jamersc.springboot.hcm_system.dto.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePatchDTO {
    private String firstName;
    private String lastName;
    @Email(message = "Email should be valid")
    private String email;
    private Long jobId;
    private LocalDate hiredDate;
    @Min(value = 0, message = "Salary must be a non-negative value")
    private Double salary;
}
