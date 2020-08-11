package com.tr.agit.hrapp.model.exception;

public class RoleNotFoundException extends Exception {

    public RoleNotFoundException() {
        super("Member Role is not found ");
    }

    public RoleNotFoundException(Throwable cause) {
        super(cause);
    }
}