package com.jamersc.springboot.hcm_api.dto.applicant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantDto {
    private Long id;
    private String applicantFullName;
    private String phoneNumber;
    private String address;
    private String educationLevel;
    private String cvFilePath;
}
