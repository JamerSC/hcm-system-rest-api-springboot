package com.jamersc.springboot.hcm_api.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordDto {
    @NotBlank(message = "Old password is required")
    private String oldPassword;
    @NotBlank(message = "New password is required")
    @Size(min = 8, message = "New password must be at least 8 characters long.")
    private String newPassword;
}
