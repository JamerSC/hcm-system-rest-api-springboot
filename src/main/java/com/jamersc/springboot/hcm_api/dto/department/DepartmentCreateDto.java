package com.jamersc.springboot.hcm_api.dto.department;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentCreateDto {
    @NotBlank(message = "Department is required!")
    private String name;
    @NotBlank(message = "Code is required!")
    private String code;
}
