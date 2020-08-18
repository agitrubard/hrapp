package com.tr.agit.hrapp.model.dto;

import com.tr.agit.hrapp.model.enums.PermissionStatus;
import com.tr.agit.hrapp.model.enums.PermissionType;

import java.io.Serializable;
import java.time.LocalDate;

public class PermissionDto implements Serializable {

    private static final long serialVersionUID = -7996772191488623437L;
    private PermissionType type;
    private LocalDate startDate;
    private LocalDate endDate;
    private long totalDays;
    private PermissionStatus status;

    public PermissionType getType() {
        return type;
    }

    public void setType(PermissionType type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public long getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(long totalDays) {
        this.totalDays = totalDays;
    }

    public PermissionStatus getStatus() {
        return status;
    }

    public void setStatus(PermissionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PermissionDto{" +
                ", type=" + type +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalDays=" + totalDays +
                ", status=" + status +
                '}';
    }
}