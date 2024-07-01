package com.smods.backend.model;

import com.smods.backend.model.composite_key.PlanModulePreassignedGpaKey;
import jakarta.persistence.*;

@Entity
@Table(name = "PLAN_MODULE_PREASSIGNED_GPA")
@IdClass(PlanModulePreassignedGpaKey.class)
public class PlanModulePreassignedGpa {

    @Id
    @Column(name = "MID")
    private String moduleId;

    @Id
    @Column(name = "PID")
    private Long planId;

    @Id
    @Column(name = "UID")
    private Long userId;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PID", referencedColumnName = "PID", insertable = false, updatable = false),
            @JoinColumn(name = "UID", referencedColumnName = "UID", insertable = false, updatable = false)
    })
    private Plan plan;

    @ManyToOne
    @JoinColumn(name = "MID", insertable = false, updatable = false)
    private Module module;

    @Column(name = "GPA")
    private Float gpa;

    @Column(name = "TERM")
    private String term;

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

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Float getGpa() {
        return gpa;
    }

    public void setGpa(Float gpa) {
        this.gpa = gpa;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
