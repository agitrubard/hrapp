package com.tr.agit.hrapp.model.exception;

public class MemberAlreadyExistsException extends Exception {

    private static final long serialVersionUID = 4979802001019352211L;

    public MemberAlreadyExistsException() {
        super("Member is already exists!");
    }

    public MemberAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}