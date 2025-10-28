package com.jamersc.springboot.hcm_api.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AttendanceStatus {
    PRESENT("Present"),
    ABSENT("Absent"),
    ON_LEAVE("On Leave");

    public final String displayAttendanceStatusValue;

    AttendanceStatus(String displayAttendanceStatusValue) {
        this.displayAttendanceStatusValue = displayAttendanceStatusValue;
    }

    @JsonValue
    public String getDisplayAttendanceStatusValue() {
        return displayAttendanceStatusValue;
    }

    @JsonCreator
    public static AttendanceStatus fromValue(String value) {
        for (AttendanceStatus status : AttendanceStatus.values()) {
            if (status.name().equalsIgnoreCase(value) || status.displayAttendanceStatusValue.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown attendance status: " + value);
    }
}
