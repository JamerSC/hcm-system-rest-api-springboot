package com.jamersc.springboot.hcm_system.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum LeaveStatus {
    PENDING("Pending"),
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
}
