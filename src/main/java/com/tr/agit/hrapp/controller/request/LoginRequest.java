package com.tr.agit.hrapp.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = 868559732900962166L;
    private String username;
    private String password;
}