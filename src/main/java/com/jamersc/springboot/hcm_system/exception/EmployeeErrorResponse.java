package com.jamersc.springboot.hcm_system.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeErrorResponse {
    private int status;
    private String message;
    private Long timestamp;
}
