package com.example.audit_service.consumer;

import com.example.audit_service.entity.AuditLog;
import com.example.audit_service.event.PolicyEvent;
import com.example.audit_service.service.AuditLogService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PolicyEventConsumer {

    private final AuditLogService auditLogService;

    public PolicyEventConsumer(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @KafkaListener(
            topics = "governance-events",
            groupId = "audit-group"
    )
    public void consumeEvent(PolicyEvent event) {

        System.out.println("Received Event: " + event.getEventType());

        AuditLog auditLog = new AuditLog();

        auditLog.setEventType(event.getEventType());
        auditLog.setPolicyId(event.getPolicyId());
        auditLog.setActor(event.getActor());
        auditLog.setTimestamp(event.getTimestamp());

        auditLogService.saveAuditLog(auditLog);
    }
}
