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
public class DepartmentDto {
    private Long id;
    @NotBlank(message = "Department is required!")
    private String name;
    private String code;
}
