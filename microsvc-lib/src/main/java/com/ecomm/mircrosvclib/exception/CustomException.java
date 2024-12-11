package com.ecomm.mircrosvclib.exception;

public class CustomException extends Exception {
    private String statusCode;
    private String reason;

    public CustomException(String message, String statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public CustomException(String message, String statusCode, String reason) {
        super(message);
        this.statusCode = statusCode;
        this.reason = reason;
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getReason() {
        return reason;
    }
}
