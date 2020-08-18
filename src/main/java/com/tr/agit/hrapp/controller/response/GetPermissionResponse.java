package com.tr.agit.hrapp.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tr.agit.hrapp.model.enums.PermissionStatus;
import com.tr.agit.hrapp.model.enums.PermissionType;

import java.time.LocalDate;

public class GetPermissionResponse {

    private PermissionType type;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd.MM.yyyy")
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
        return "GetPermissionResponse{" +
                "type=" + type +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalDays=" + totalDays +
                ", status=" + status +
                '}';
    }
}
