package com.example.governance_policy.exception;

public class PolicyNotFoundException extends RuntimeException {

    public PolicyNotFoundException(Long id) {
        super("Policy with ID " + id + " not found.");
    }

}
