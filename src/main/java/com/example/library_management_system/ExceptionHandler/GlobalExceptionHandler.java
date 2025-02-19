package com.example.library_management_system.ExceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.library_management_system.misc.ErrorObject;
import com.example.library_management_system.misc.GlobalConstant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(exception = GlobalException.class)
    public ResponseEntity<ErrorObject> errorHandler(GlobalException e) {
        return ResponseEntity.status(e.statusCode).body(new ErrorObject(GlobalConstant.FAILURE, e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorObject> errorHandler(Exception e) {
        return ResponseEntity.status(500).body(new ErrorObject(GlobalConstant.FAILURE, e.getMessage()));
    }
}
