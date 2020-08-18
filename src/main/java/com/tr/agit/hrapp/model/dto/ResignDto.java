package com.tr.agit.hrapp.model.dto;

import com.tr.agit.hrapp.model.enums.ResignStatus;

import java.io.Serializable;
import java.time.LocalDate;

public class ResignDto implements Serializable {

    private static final long serialVersionUID = -5264704565088109371L;
    private LocalDate resignDate;
    private long totalWorkingDays;
    private ResignStatus status;

    public LocalDate getResignDate() {
        return resignDate;
    }

    public void setResignDate(LocalDate resignDate) {
        this.resignDate = resignDate;
    }

    public long getTotalWorkingDays() {
        return totalWorkingDays;
    }

    public void setTotalWorkingDays(long totalWorkingDays) {
        this.totalWorkingDays = totalWorkingDays;
    }

    public ResignStatus getStatus() {
        return status;
    }

    public void setStatus(ResignStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResignDto{" +
                ", resignDate=" + resignDate +
                ", totalWorkingDays=" + totalWorkingDays +
                ", status=" + status +
                '}';
    }
}