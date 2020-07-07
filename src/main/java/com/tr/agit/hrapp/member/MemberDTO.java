package com.tr.agit.hrapp.member;

import java.io.Serializable;
import java.util.Objects;

public class MemberDTO implements Serializable {

    private static final long serialVersionUID = 5601565721402723456L;
    private String name;
    private String surname;
    private String password;
    private String email;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberDTO)) return false;
        MemberDTO memberDTO = (MemberDTO) o;
        return Objects.equals(getName(), memberDTO.getName()) &&
                Objects.equals(getSurname(), memberDTO.getSurname()) &&
                Objects.equals(getPassword(), memberDTO.getPassword()) &&
                Objects.equals(getEmail(), memberDTO.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname(), getPassword(), getEmail());
    }
}
