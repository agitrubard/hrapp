package com.tr.agit.hrapp.controller.request;

import java.io.Serializable;

public class LoginRequest implements Serializable {

    private static final long serialVersionUID = 868559732900962166L;
    private String email;
    private String password;
    private String tempPassword;

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

    public String getTempPassword() {
        return tempPassword;
    }

    public void setTempPassword(String tempPassword) {
        this.tempPassword = tempPassword;
    }
}
