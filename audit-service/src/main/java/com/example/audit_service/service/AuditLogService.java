package com.example.audit_service.service;

import com.example.audit_service.entity.AuditLog;
import com.example.audit_service.exception.AuditLogNotFoundException;
import com.example.audit_service.repository.AuditLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditLogService {

    private final AuditLogRepository repository;

    public AuditLogService(AuditLogRepository repository) {
        this.repository = repository;
    }

    public AuditLog saveAuditLog(AuditLog auditLog) {
        return repository.save(auditLog);
    }

    public List<AuditLog> getAllAuditLogs() {
        return repository.findAll();
    }

    public AuditLog getAuditLogById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AuditLogNotFoundException(id));
    }

}