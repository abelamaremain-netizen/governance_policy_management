package com.example.governance_policy.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KafkaTopicConfig {


    @Bean
    public NewTopic governanceEventsTopic(){

        return new NewTopic(
                "governance-events",
                1,
                (short) 1
        );
    }
}
