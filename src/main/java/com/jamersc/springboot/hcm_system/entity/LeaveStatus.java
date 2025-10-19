package com.jamersc.springboot.hcm_system.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum LeaveStatus {
    SUBMITTED("Submitted"),
    CANCELLED("Cancelled"),
    ON_HOLD("On Hold"),
    APPROVED("Approved"),
    REJECTED("Rejected");

    public final String displayLeaveStatusValue;

    LeaveStatus(String displayLeaveStatusValue) {
        this.displayLeaveStatusValue = displayLeaveStatusValue;
    }

    @JsonValue
    public String getDisplayLeaveStatusValue() {
        return displayLeaveStatusValue;
    }

    @JsonCreator
    public static LeaveStatus fromValue(String value) {
        for (LeaveStatus status : LeaveStatus.values()) {
            if (status.name().equalsIgnoreCase(value) || status.displayLeaveStatusValue.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown leave status: " + value);
    }
}
