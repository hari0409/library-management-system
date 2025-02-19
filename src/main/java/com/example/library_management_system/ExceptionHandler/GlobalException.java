package com.example.library_management_system.ExceptionHandler;

public class GlobalException extends RuntimeException {
    public String msg;
    public Integer statusCode;

    public GlobalException(String msg, Integer statusCode) {
        super(msg);
        this.statusCode = statusCode;
    }
}
