package com.tr.agit.hrapp.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tr.agit.hrapp.model.enums.ResignStatus;

import java.time.LocalDate;

public class GetResignedResponse {

    private long memberId;
    private String name;
    private String surname;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate startWorkingDate;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate endWorkingDate;
    private long totalWorkingDays;
    private ResignStatus status;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getStartWorkingDate() {
        return startWorkingDate;
    }

    public void setStartWorkingDate(LocalDate startWorkingDate) {
        this.startWorkingDate = startWorkingDate;
    }

    public LocalDate getEndWorkingDate() {
        return endWorkingDate;
    }

    public void setEndWorkingDate(LocalDate endWorkingDate) {
        this.endWorkingDate = endWorkingDate;
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
        return "GetResignedResponse{" +
                "memberId=" + memberId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", startWorkingDate=" + startWorkingDate +
                ", endWorkingDate=" + endWorkingDate +
                ", totalWorkingDays=" + totalWorkingDays +
                ", status=" + status +
                '}';
    }
}