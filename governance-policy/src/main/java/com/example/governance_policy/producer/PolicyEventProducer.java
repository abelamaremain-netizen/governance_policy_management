package com.example.governance_policy.producer;


import com.example.governance_policy.event.PolicyEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class PolicyEventProducer {


    private final KafkaTemplate<String, PolicyEvent> kafkaTemplate;


    private final String TOPIC = "governance-events";


    public PolicyEventProducer(KafkaTemplate<String, PolicyEvent> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendEvent(PolicyEvent event) {

        kafkaTemplate.send(
                TOPIC,
                event
        );
    }
}