package com.tr.agit.hrapp.model.exception;

public class RoleNotFoundException extends Exception {

    private static final long serialVersionUID = 6690431631379341107L;

    public RoleNotFoundException() {
        super("Member's role is not found!");
    }

    public RoleNotFoundException(Throwable cause) {
        super(cause);
    }
}