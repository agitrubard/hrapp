package com.tr.agit.hrapp.model.dto;

import java.io.Serializable;
import java.util.Objects;

public class MemberDto implements Serializable {

    private static final long serialVersionUID = 5601565721402723456L;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String tempPassword;
    private String newPassword;
    private String newPasswordConfirm;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getTempPassword() {
        return tempPassword;
    }

    public void setTempPassword(String tempPassword) {
        this.tempPassword = tempPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm;
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", tempPassword='" + tempPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", newPasswordConfirm='" + newPasswordConfirm + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberDto)) return false;
        MemberDto memberDto = (MemberDto) o;
        return Objects.equals(getEmail(), memberDto.getEmail()) &&
                Objects.equals(getPassword(), memberDto.getPassword()) &&
                Objects.equals(getName(), memberDto.getName()) &&
                Objects.equals(getSurname(), memberDto.getSurname()) &&
                Objects.equals(getTempPassword(), memberDto.getTempPassword()) &&
                Objects.equals(getNewPassword(), memberDto.getNewPassword()) &&
                Objects.equals(getNewPasswordConfirm(), memberDto.getNewPasswordConfirm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword(), getName(), getSurname(), getTempPassword(), getNewPassword(), getNewPasswordConfirm());
    }
}
