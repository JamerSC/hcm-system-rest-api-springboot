package com.jamersc.springboot.hcm_api.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
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

    @JsonCreator
    public static LeaveType fromValue(String value) {
        for (LeaveType type : LeaveType.values()) {
            if (type.name().equalsIgnoreCase(value) || type.displayLeaveTypeValue.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown leave type: " + value);
    }
}
