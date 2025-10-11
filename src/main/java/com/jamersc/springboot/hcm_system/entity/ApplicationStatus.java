package com.jamersc.springboot.hcm_system.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ApplicationStatus {
    NEW("Application Submitted"),
    INITIAL_QUALIFICATION("Initial Qualification"),
    FIRST_INTERVIEW("First Interview"),
    SECOND_INTERVIEW("Second Interview"),
    CONTRACT_PROPOSAL("Contract Proposal"),
    CONTRACT_SIGNED("Contract Signed"),
    APPROVED("Approved"),
    REJECTED("Rejected"),
    HIRED("Hired"),
    WITHDRAWN("Withdrawn");

    private final String displayApplicationStatusValue;

    ApplicationStatus(String displayApplicationStatusValue) {
        this.displayApplicationStatusValue = displayApplicationStatusValue;
    }

    @JsonValue
    public String getDisplayApplicationStatusValue() {
        return displayApplicationStatusValue;
    }
}
