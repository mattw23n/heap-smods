package com.smods.backend.controller;

import com.smods.backend.dto.LoginRequest;
import com.smods.backend.dto.UserDTO;
import com.smods.backend.model.Plan;
import com.smods.backend.model.composite_key.PlanId;
import com.smods.backend.service.PlanService;
import com.smods.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
public class PlanController {
    private final PlanService planService;
    private final UserService userService;

    @Autowired
    public PlanController(PlanService planService, UserService userService) {
        this.planService = planService;
        this.userService = userService;
    }

    @GetMapping
    public List<Plan> getAllPlans() {
        return planService.getAllPlans();
    }

    @GetMapping("/{userId}/{planId}")
    public ResponseEntity<Plan> getPlanById(@PathVariable Long userId, @PathVariable Long planId) {
        PlanId id = new PlanId(userId, planId);
        Plan plan = planService.getPlanById(id).orElseThrow(() -> new RuntimeException("Plan not found with id: " + id));
        return ResponseEntity.ok(plan);
    }

    @PostMapping
    public Plan createPlan(@RequestBody Plan plan) {
        return planService.createPlan(plan);
    }

    @PutMapping("/{userId}/{planId}")
    public ResponseEntity<Plan> updatePlan(@PathVariable Long userId, @PathVariable Long planId, @RequestBody Plan planDetails) {
        PlanId id = new PlanId(userId, planId);
        Plan updatedPlan = planService.updatePlan(id, planDetails);
        return ResponseEntity.ok(updatedPlan);
    }

    @DeleteMapping("/{userId}/{planId}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long userId, @PathVariable Long planId) {
        PlanId id = new PlanId(userId, planId);
        planService.deletePlan(id);
        return ResponseEntity.noContent().build();
    }
}
