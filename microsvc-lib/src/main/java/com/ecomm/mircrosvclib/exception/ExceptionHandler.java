package com.ecomm.mircrosvclib.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class ExceptionHandler {

    public static String getDuplicateFieldErrorMessage(DataIntegrityViolationException e) {
        String message = e.getRootCause() != null ? e.getRootCause().getMessage() : e.getMessage();

        if (message.contains("key") && message.contains("Duplicate entry")) {
            if (message.contains("email_UNIQUE")) {
                return "The email address is already in use. Please provide a different email.";
            } else if (message.contains("USER_NAME_UNIQUE")) {
                return "The username is already in use. Please choose a different username.";
            } else if (message.contains("MOBILE_UNIQUE")) {
                return "The mobile number is already in use. Please provide a different mobile number.";
            }
        }
        return "A unique constraint violation occurred. Please check your input and try again.";
    }
}
