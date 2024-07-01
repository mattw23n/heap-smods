package com.smods.backend.service;

import com.smods.backend.model.Plan;
import com.smods.backend.model.composite_key.PlanId;
import com.smods.backend.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlanService {

    private final PlanRepository planRepository;

    @Autowired
    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public Optional<Plan> getPlanById(PlanId planId) {
        return planRepository.findById(planId);
    }

    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }

    public Plan createPlan(Plan plan) {
        return planRepository.save(plan);
    }

    public Plan updatePlan(PlanId planId, Plan planDetails) {
        return planRepository.findById(planId).map(plan -> {
            plan.setPname(planDetails.getPname());
            plan.setDegree(planDetails.getDegree());
            plan.setTrack(planDetails.getTrack());
            return planRepository.save(plan);
        }).orElseThrow(() -> new RuntimeException("Plan not found with id: " + planId));
    }

    public void deletePlan(PlanId planId) {
        planRepository.deleteById(planId);
    }

    public List<String> getTerms(PlanId planId) {
        return planRepository.findById(planId)
                .map(plan -> plan.getPlanModuleGPAs().stream()
                        .map(gpa -> gpa.getTerm())
                        .distinct()
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new RuntimeException("Plan not found with id: " + planId));
    }
}
