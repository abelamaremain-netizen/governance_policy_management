package com.example.audit_service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuditLogNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(AuditLogNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleRuntime(RuntimeException ex) {
        return ex.getMessage();
    }

}
