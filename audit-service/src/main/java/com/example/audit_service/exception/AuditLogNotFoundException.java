package com.example.audit_service.exception;


public class AuditLogNotFoundException extends RuntimeException {

    public AuditLogNotFoundException(Long id) {
        super("Audit log with ID " + id + " not found.");
    }

}



