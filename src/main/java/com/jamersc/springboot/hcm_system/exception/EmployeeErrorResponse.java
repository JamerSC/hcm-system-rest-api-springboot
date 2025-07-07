package com.jamersc.springboot.hcm_system.exception;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeErrorResponse {
    private int status;
    private String message;
    private Long timestamp;
}
