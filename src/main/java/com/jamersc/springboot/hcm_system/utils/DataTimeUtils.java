package com.jamersc.springboot.hcm_system.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

    /**
     * Date and Time Helpers 📅
     * Most common uses. Instead of repeating date formatting logic
     * in every service or controller, you can centralize it here.
     * Example Usage: Formatting a Date or LocalDate into a specific String pattern,
     * or converting timestamps between different time zones.
     **/

public class DataTimeUtils {

    public static final DateTimeFormatter FORMATTER_LOCAL_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter FORMATTER_LOCAL_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String formatLocalDate(LocalDate date) {
        return date.format(FORMATTER_LOCAL_DATE);
    }

    public static String formatLocalDateTime(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER_LOCAL_DATE_TIME);
    }

}
