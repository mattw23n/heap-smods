package com.smods.backend.service;

import com.smods.backend.model.*;
import com.smods.backend.model.composite_key.PlanKey;
import com.smods.backend.model.composite_key.PlanModuleGPAKey;
import com.smods.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanService {

    private final PlanRepository planRepository;
    private final PlanModuleGPARepository planModuleGPARepository;
    private final UserRepository userRepository;
    private final PreRequisiteRepository preRequisiteRepository;
    private final CoRequisiteRepository coRequisiteRepository;
    private final MutuallyExclusiveRepository mutuallyExclusiveRepository;

    @Autowired
    public PlanService(PlanRepository planRepository, PlanModuleGPARepository planModuleGPARepository, UserRepository userRepository,
                       PreRequisiteRepository preRequisiteRepository, CoRequisiteRepository coRequisiteRepository,
                       MutuallyExclusiveRepository mutuallyExclusiveRepository) {
        this.planRepository = planRepository;
        this.planModuleGPARepository = planModuleGPARepository;
        this.userRepository = userRepository;
        this.preRequisiteRepository = preRequisiteRepository;
        this.coRequisiteRepository = coRequisiteRepository;
        this.mutuallyExclusiveRepository = mutuallyExclusiveRepository;
    }

    public List<Plan> getAllPlansByUser(Long userId) {
        return planRepository.findByUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
    }

    public Plan createPlan(Long userId, Plan plan) {
        plan.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
        return planRepository.save(plan);
    }

    public PlanModuleGPA addModule(Long planId, Long userId, String moduleId, int term) {
        PlanKey planKey = new PlanKey(planId, userId);
        Plan plan = planRepository.findById(planKey)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        PlanModuleGPAKey id = new PlanModuleGPAKey(planKey, moduleId);

        // Check if the module already exists in the plan
        boolean moduleExists = planModuleGPARepository.existsById(id);
        if (moduleExists) {
            throw new RuntimeException("Module already exists in the plan");
        }

        // Validate prerequisites and collect unsatisfied ones
        List<PreRequisite> unsatisfiedPrerequisites = validatePrerequisites(plan, moduleId, term);
        if (!unsatisfiedPrerequisites.isEmpty()) {
            StringBuilder message = new StringBuilder("Pre-requisites for " + moduleId + " not satisfied: ");
            for (PreRequisite preReq : unsatisfiedPrerequisites) {
                message.append(preReq.getModuleId2()).append(" ");
            }
            throw new RuntimeException(message.toString().trim());
        }

        // Validate co-requisites and collect unsatisfied ones
        List<CoRequisite> unsatisfiedCoRequisites = validateCoRequisites(plan, moduleId, term);
        if (!unsatisfiedCoRequisites.isEmpty()) {
            StringBuilder message = new StringBuilder("Co-requisites for " + moduleId + " not satisfied: ");
            for (CoRequisite coReq : unsatisfiedCoRequisites) {
                message.append(coReq.getModuleId2()).append(" ");
            }
            throw new RuntimeException(message.toString().trim());
        }

        // Validate mutually exclusive modules and collect conflicts
        List<MutuallyExclusive> mutuallyExclusiveConflicts = validateMutuallyExclusive(plan, moduleId);
        if (!mutuallyExclusiveConflicts.isEmpty()) {
            StringBuilder message = new StringBuilder("Conflicting mutually exclusive modules: ");
            for (MutuallyExclusive conflict : mutuallyExclusiveConflicts) {
                message.append(conflict.getModuleId2()).append(" ");
            }
            throw new RuntimeException(message.toString().trim());
        }

        PlanModuleGPA planModuleGPA = new PlanModuleGPA();
        planModuleGPA.setId(id);
        planModuleGPA.setPlan(plan);
        planModuleGPA.setTerm(term);

        return planModuleGPARepository.save(planModuleGPA);
    }

    private List<PreRequisite> validatePrerequisites(Plan plan, String moduleId, int term) {
        List<PreRequisite> preRequisites = preRequisiteRepository.findByModuleId(moduleId);
        List<PreRequisite> unsatisfiedPreRequisites = new ArrayList<>();

        for (PreRequisite preRequisite : preRequisites) {
            boolean preRequisiteSatisfied = false;

            for (PlanModuleGPA existingModule : plan.getPlanModuleGPAs()) {
                if (existingModule.getModule().getModuleId().equals(preRequisite.getModuleId2()) &&
                        existingModule.getTerm() < term) {
                    preRequisiteSatisfied = true;
                    break;
                }
            }

            if (!preRequisiteSatisfied) {
                unsatisfiedPreRequisites.add(preRequisite);
            }
        }

        return unsatisfiedPreRequisites;
    }

    private List<CoRequisite> validateCoRequisites(Plan plan, String moduleId, int term) {
        List<CoRequisite> coRequisites = coRequisiteRepository.findByModuleId(moduleId);
        List<CoRequisite> unsatisfiedCoRequisites = new ArrayList<>();

        for (CoRequisite coRequisite : coRequisites) {
            boolean coRequisiteSatisfied = false;

            for (PlanModuleGPA existingModule : plan.getPlanModuleGPAs()) {
                if (existingModule.getModule().getModuleId().equals(coRequisite.getModuleId2()) &&
                        existingModule.getTerm() == term) {
                    coRequisiteSatisfied = true;
                    break;
                }
            }

            if (!coRequisiteSatisfied) {
                unsatisfiedCoRequisites.add(coRequisite);
            }
        }

        return unsatisfiedCoRequisites;
    }

    private List<MutuallyExclusive> validateMutuallyExclusive(Plan plan, String moduleId) {
        List<MutuallyExclusive> mutuallyExclusiveModules = mutuallyExclusiveRepository.findByModuleId(moduleId);
        List<MutuallyExclusive> conflicts = new ArrayList<>();

        for (MutuallyExclusive mutuallyExclusive : mutuallyExclusiveModules) {
            for (PlanModuleGPA existingModule : plan.getPlanModuleGPAs()) {
                if (existingModule.getModule().getModuleId().equals(mutuallyExclusive.getModuleId2())) {
                    conflicts.add(mutuallyExclusive);
                }
            }
        }

        return conflicts;
    }
}