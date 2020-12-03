package com.tr.agit.hrapp.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ChangePasswordRequest implements Serializable {

    private static final long serialVersionUID = 7581423611922700720L;
    private String username;
    private String password;
    private String newPassword;
}