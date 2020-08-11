package com.tr.agit.hrapp.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tr.agit.hrapp.model.enums.DemandType;

import java.io.Serializable;
import java.time.LocalDate;

public class UpdateDemandRequest implements Serializable {

    private static final long serialVersionUID = -4241921580209031521L;
    private DemandType type;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate endDate;

    public DemandType getType() {
        return type;
    }

    public void setType(DemandType type) {
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

    @Override
    public String toString() {
        return "UpdateDemandRequest{" +
                "type=" + type +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}