package com.jamersc.springboot.hcm_system.utils;

import java.util.regex.Pattern;

public class ValidationUtils {

    /**
     * Validation and Data Conversion Helpers ✅
     * Useful for custom, non-JPA validation logic or for converting data types.
     * Example Usage: A utility to check if a string is a valid email
     **/

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
