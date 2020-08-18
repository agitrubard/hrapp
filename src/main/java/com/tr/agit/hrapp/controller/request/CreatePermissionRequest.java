package com.tr.agit.hrapp.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tr.agit.hrapp.model.enums.PermissionType;

import java.io.Serializable;
import java.time.LocalDate;

public class CreatePermissionRequest implements Serializable {

    private static final long serialVersionUID = 4439746317150162978L;
    private PermissionType type;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate endDate;

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

    @Override
    public String toString() {
        return "CreatePermissionRequest{" +
                "type=" + type +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}