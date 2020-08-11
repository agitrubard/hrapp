package com.tr.agit.hrapp.model.exception;

public class MemberAlreadyExistsException extends Exception {

    public MemberAlreadyExistsException() {
        super("Member is already exists!");
    }

    public MemberAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}