package com.tr.agit.hrapp.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tr.agit.hrapp.model.enums.MemberStatus;

import java.time.LocalDate;

public class GetMemberResponse {

    private long memberId;
    private String email;
    private String username;
    private String name;
    private String surname;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthdate;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate startWorkingDate;
    private MemberStatus status;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public LocalDate getStartWorkingDate() {
        return startWorkingDate;
    }

    public void setStartWorkingDate(LocalDate startWorkingDate) {
        this.startWorkingDate = startWorkingDate;
    }

    public MemberStatus getStatus() {
        return status;
    }

    public void setStatus(MemberStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "GetMemberResponse{" +
                "memberId=" + memberId +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate=" + birthdate +
                ", startWorkingDate=" + startWorkingDate +
                ", status=" + status +
                '}';
    }
}