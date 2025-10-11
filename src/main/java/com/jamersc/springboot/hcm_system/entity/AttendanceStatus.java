package com.jamersc.springboot.hcm_system.entity;


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
}
