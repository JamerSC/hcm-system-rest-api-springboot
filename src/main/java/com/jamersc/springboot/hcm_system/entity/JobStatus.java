package com.jamersc.springboot.hcm_system.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum JobStatus {
    CREATED("Job Created"),
    OPEN("Open for Applications"),
    CLOSED("Job Filled"),
    FILLED("Hiring Completed"),
    ON_HOLD("Temporarily Paused");

    private final String displayJobStatusValue;

    JobStatus(String displayJobStatusValue) {
        this.displayJobStatusValue = displayJobStatusValue;
    }

    // BEST PRACTICE: Use @JsonValue to send a user-friendly string to the client.
    // When Spring serializes the entity, it uses this method to get the JSON value.
    @JsonValue
    public String getDisplayJobStatusValue() {
        return displayJobStatusValue;
    }

    // BEST PRACTICE: This static method allows easy conversion from the display value back to the enum,
    // useful if you map the display value on the DTO.
    public static JobStatus fromDisplayJobStatusValue(String value) {
        for (JobStatus status : JobStatus.values()) {
            if (status.name().equalsIgnoreCase(value) || status.displayJobStatusValue.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown display value for JobStatus: " + value);
    }
}
