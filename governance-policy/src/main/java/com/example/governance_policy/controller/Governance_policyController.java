package com.example.governance_policy.controller;

import com.example.governance_policy.dto.CreatePolicyRequest;
import com.example.governance_policy.entity.Governance_policyEntity;
import com.example.governance_policy.service.Governance_policyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policies")
public class Governance_policyController {


    private final Governance_policyService service;


    public Governance_policyController(Governance_policyService service) {
        this.service = service;
    }


    @PostMapping
    public Governance_policyEntity createPolicy(
            @Valid @RequestBody CreatePolicyRequest request) {

        return service.createPolicy(request);
    }


    @GetMapping
    public List<Governance_policyEntity> getAllPolicies() {

        return service.getAllPolicies();
    }


    @GetMapping("/{id}")
    public Governance_policyEntity getPolicyById(
            @PathVariable Long id) {

        return service.getPolicyById(id);
    }


    @PostMapping("/{id}/submit")
    public Governance_policyEntity submitPolicy(
            @PathVariable Long id) {

        return service.submitPolicy(id);
    }


    @PostMapping("/{id}/approve")
    public Governance_policyEntity approvePolicy(
            @PathVariable Long id) {

        return service.approvePolicy(id);
    }


    @PostMapping("/{id}/reject")
    public Governance_policyEntity rejectPolicy(
            @PathVariable Long id) {

        return service.rejectPolicy(id);
    }
}