package com.tr.agit.hrapp.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tr.agit.hrapp.model.enums.DemandType;

import java.io.Serializable;
import java.time.LocalDate;

public class CreateDemandRequest  implements Serializable {

    private static final long serialVersionUID = 4439746317150162978L;
    private DemandType demandType;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate endDate;

    public DemandType getDemandType() {
        return demandType;
    }

    public void setDemandType(DemandType demandType) {
        this.demandType = demandType;
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
}
