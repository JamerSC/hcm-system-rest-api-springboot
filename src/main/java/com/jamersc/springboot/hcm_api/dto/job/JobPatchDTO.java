package com.jamersc.springboot.hcm_api.dto.job;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobPatchDTO {
    private String title;
    private String description;
    private String requirements;
    private String location;
    private Long departmentId;
}
