package com.tr.agit.hrapp.model.exception;

public class PermissionNotFoundException extends Exception {

    private static final long serialVersionUID = -169876979396344849L;

    public PermissionNotFoundException() {
        super("Permission is not found!");
    }

    public PermissionNotFoundException(Throwable cause) {
        super(cause);
    }
}