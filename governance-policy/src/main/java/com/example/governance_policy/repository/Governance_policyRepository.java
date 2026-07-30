package com.example.governance_policy.repository;

import com.example.governance_policy.entity.Governance_policyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Governance_policyRepository extends JpaRepository<Governance_policyEntity, Long> {

}