package com.smods.backend.model.composite_key;

import java.io.Serializable;
import java.util.Objects;

public class PlanModuleGpaKey implements Serializable {

    private String moduleId;
    private Long planId;
    private Long userId;

    public PlanModuleGpaKey() {}

    public PlanModuleGpaKey(String moduleId, Long planId, Long userId) {
        this.moduleId = moduleId;
        this.planId = planId;
        this.userId = userId;
    }

    // Getters and setters
    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanModuleGpaKey that = (PlanModuleGpaKey) o;
        return Objects.equals(moduleId, that.moduleId) &&
                Objects.equals(planId, that.planId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleId, planId, userId);
    }
}
