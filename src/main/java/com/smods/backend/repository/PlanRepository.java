package com.smods.backend.repository;

import com.smods.backend.model.Plan;
import com.smods.backend.model.User;
import com.smods.backend.model.composite_key.PlanKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, PlanKey> {
    List<Plan> findByUser(User user);

    boolean existsByUserAndPlanName(User user, String planName);

    @Query("SELECT COALESCE(MAX(p.planId.planId), 0) FROM Plan p WHERE p.user.userId = :userId")
    Long findMaxPlanIdByUserId(Long userId);
}