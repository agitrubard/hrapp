package com.tr.agit.hrapp.controller.request;

import java.io.Serializable;

public class AddMembersRequest implements Serializable {

    private static final long serialVersionUID = -1429459006887569939L;
    private String email;
    private String password;
    private String name;
    private String surname;

    @Override
    public String toString() {
        return "SignupRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

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
}
