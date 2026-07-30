package com.example.governance_policy.service;


import com.example.governance_policy.dto.CreatePolicyRequest;
import com.example.governance_policy.entity.Governance_policyEntity;
import com.example.governance_policy.event.PolicyEvent;
import com.example.governance_policy.exception.PolicyNotFoundException;
import com.example.governance_policy.producer.PolicyEventProducer;
import com.example.governance_policy.repository.Governance_policyRepository;

import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;


@Service
public class Governance_policyService {


    private final Governance_policyRepository repository;

    private final PolicyEventProducer producer;



    public Governance_policyService(
            Governance_policyRepository repository,
            PolicyEventProducer producer) {

        this.repository = repository;
        this.producer = producer;
    }



    public Governance_policyEntity createPolicy(CreatePolicyRequest request) {


        Governance_policyEntity policy = new Governance_policyEntity();


        policy.setTitle(request.getTitle());
        policy.setDescription(request.getDescription());
        policy.setCreatedBy(request.getCreatedBy());


        Governance_policyEntity savedPolicy = repository.save(policy);



        producer.sendEvent(
                new PolicyEvent(
                        "policy-created",
                        savedPolicy.getId(),
                        savedPolicy.getCreatedBy(),
                        LocalDateTime.now()
                )
        );


        return savedPolicy;
    }




    public List<Governance_policyEntity> getAllPolicies() {

        return repository.findAll();
    }




    public Governance_policyEntity getPolicyById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new PolicyNotFoundException(id));
    }





    public Governance_policyEntity submitPolicy(Long id) {


        Governance_policyEntity policy = getPolicyById(id);


        if(policy.getStatus() != Governance_policyEntity.Status.DRAFT){

            throw new RuntimeException(
                    "Only DRAFT policies can be submitted."
            );
        }


        policy.setStatus(
                Governance_policyEntity.Status.PENDING_APPROVAL
        );


        Governance_policyEntity savedPolicy = repository.save(policy);



        producer.sendEvent(
                new PolicyEvent(
                        "policy-submitted",
                        savedPolicy.getId(),
                        "system",
                        LocalDateTime.now()
                )
        );



        return savedPolicy;
    }






    public Governance_policyEntity approvePolicy(Long id) {


        Governance_policyEntity policy = getPolicyById(id);



        if(policy.getStatus() != Governance_policyEntity.Status.PENDING_APPROVAL){

            throw new RuntimeException(
                    "Only PENDING_APPROVAL policies can be approved."
            );
        }



        policy.setStatus(
                Governance_policyEntity.Status.APPROVED
        );



        Governance_policyEntity savedPolicy = repository.save(policy);



        producer.sendEvent(
                new PolicyEvent(
                        "policy-approved",
                        savedPolicy.getId(),
                        "system",
                        LocalDateTime.now()
                )
        );



        return savedPolicy;
    }






    public Governance_policyEntity rejectPolicy(Long id) {


        Governance_policyEntity policy = getPolicyById(id);



        if(policy.getStatus() != Governance_policyEntity.Status.PENDING_APPROVAL){

            throw new RuntimeException(
                    "Only PENDING_APPROVAL policies can be rejected."
            );
        }



        policy.setStatus(
                Governance_policyEntity.Status.REJECTED
        );



        Governance_policyEntity savedPolicy = repository.save(policy);



        producer.sendEvent(
                new PolicyEvent(
                        "policy-rejected",
                        savedPolicy.getId(),
                        "system",
                        LocalDateTime.now()
                )
        );



        return savedPolicy;
    }

}