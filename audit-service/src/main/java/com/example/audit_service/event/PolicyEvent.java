package com.example.audit_service.event;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class PolicyEvent {

    private String eventType;

    private Long policyId;

    private String actor;

    private LocalDateTime timestamp;


    public PolicyEvent() {
    }


    public PolicyEvent(String eventType, Long policyId, String actor, LocalDateTime timestamp) {

        this.eventType = eventType;
        this.policyId = policyId;
        this.actor = actor;
        this.timestamp = timestamp;
    }
}
