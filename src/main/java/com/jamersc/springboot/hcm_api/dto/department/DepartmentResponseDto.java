package com.jamersc.springboot.hcm_api.dto.department;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponseDto {
    private Long id;
    private String name;
    private String code;
    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Manila")
    private Date createdAt;
    private String updatedBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Manila")
    private Date updatedAt;
}
