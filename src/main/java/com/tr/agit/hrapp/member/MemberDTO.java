package com.tr.agit.hrapp.member;

import java.io.Serializable;

public class MemberDTO implements Serializable {

    private static final long serialVersionUID = 5601565721402723456L;
    private String name;
    private String surname;
    private String password;
    private String email;

    @Override
    public String toString() {
        return "MemberDTO{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
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
}
