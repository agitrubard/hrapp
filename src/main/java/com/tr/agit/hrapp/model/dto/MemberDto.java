package com.tr.agit.hrapp.model.dto;

import com.tr.agit.hrapp.model.enums.MemberStatus;

import java.io.Serializable;
import java.time.LocalDate;

public class MemberDto implements Serializable {

    private static final long serialVersionUID = 5601565721402723456L;
    private String email;
    private String username;
    private LocalDate startWorkingDate;
    private MemberStatus status;
    private String password;
    private String newPassword;
    private String name;
    private String surname;
    private LocalDate birthdate;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
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

    @Override
    public String toString() {
        return "MemberDto{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", startWorkingDate=" + startWorkingDate +
                ", status=" + status +
                ", password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}