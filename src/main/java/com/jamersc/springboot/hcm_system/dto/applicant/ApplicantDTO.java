package com.jamersc.springboot.hcm_system.dto.applicant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantDTO {
    private Long id;
    private String applicantFullName;
    private String phoneNumber;
    private String address;
    private String educationLevel;
    private String cvFilePath;
}
