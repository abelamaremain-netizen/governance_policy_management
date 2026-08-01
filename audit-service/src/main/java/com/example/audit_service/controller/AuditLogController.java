package com.example.audit_service.controller;

import com.example.audit_service.entity.AuditLog;
import com.example.audit_service.service.AuditLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audit-logs")
public class AuditLogController {


    private final AuditLogService service;

    public AuditLogController(AuditLogService service) {

        this.service = service;

    }

    @GetMapping
    public List<AuditLog> getAllAuditLogs() {
        return service.getAllAuditLogs();
    }

    @GetMapping("/{id}")
    public AuditLog getAuditLogById(@PathVariable Long id) {
        return service.getAuditLogById(id);

    }

}
