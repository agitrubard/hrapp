package com.tr.agit.hrapp.model.dto;

import com.tr.agit.hrapp.model.enums.DemandStatus;
import com.tr.agit.hrapp.model.enums.DemandType;

import java.time.LocalDateTime;

public class DemandDto {

    private long memberId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int totalTime;
    private DemandType type;
    private DemandStatus status;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
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
