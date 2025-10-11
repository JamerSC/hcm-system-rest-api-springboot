package com.jamersc.springboot.hcm_system.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum LeaveType {
    SICK_LEAVE("Sick Leave"),
    VACATION_LEAVE("Vacation Leave"),
    EMERGENCY_LEAVE("Emergency Leave"),
    PATERNITY("Paternity Leave"),
    MATERNITY("Maternity Leave");

    public final String displayLeaveTypeValue;

    LeaveType(String displayLeaveTypeValue) {
        this.displayLeaveTypeValue = displayLeaveTypeValue;
    }

    @JsonValue
    public String getDisplayLeaveTypeValue() {
        return displayLeaveTypeValue;
    }
}
