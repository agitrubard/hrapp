package com.tr.agit.hrapp.model.dto;

import com.tr.agit.hrapp.model.enums.DemandStatus;
import com.tr.agit.hrapp.model.enums.DemandType;

import java.time.LocalDate;

public class DemandDto {

    private long memberId;
    private LocalDate startDate;
    private LocalDate endDate;
    private long totalDays;
    private DemandType type;
    private DemandStatus status;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
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

    public DemandType getType() {
        return type;
    }

    public void setType(DemandType type) {
        this.type = type;
    }

    public DemandStatus getStatus() {
        return status;
    }

    public void setStatus(DemandStatus status) {
        this.status = status;
    }
}
