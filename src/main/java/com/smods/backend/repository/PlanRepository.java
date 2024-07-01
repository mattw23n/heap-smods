package com.smods.backend.repository;

import com.smods.backend.model.Plan;
import com.smods.backend.model.composite_key.PlanId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, PlanId> {
    List<Plan> findByUserId(Long userId);
}
