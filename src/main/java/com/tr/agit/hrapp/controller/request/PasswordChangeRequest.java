package com.tr.agit.hrapp.controller.request;

import java.io.Serializable;

public class PasswordChangeRequest implements Serializable {

    private static final long serialVersionUID = 7581423611922700720L;
    private String email;
    private String password;
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
}
