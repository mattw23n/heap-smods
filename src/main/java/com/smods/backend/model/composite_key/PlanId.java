package com.smods.backend.model.composite_key;

import java.io.Serializable;
import java.util.Objects;

public class PlanId implements Serializable {

    private Long id;
    private Long userId;

    public PlanId() {}

    public PlanId(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        PlanId that = (PlanId) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId);
    }
}
