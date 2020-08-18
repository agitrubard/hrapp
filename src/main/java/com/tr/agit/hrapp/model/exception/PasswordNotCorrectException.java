package com.tr.agit.hrapp.model.exception;

public class PasswordNotCorrectException extends Exception {

    private static final long serialVersionUID = -4902631980035897045L;

    public PasswordNotCorrectException() {
        super("Password is not correct!");
    }

    public PasswordNotCorrectException(Throwable cause) {
        super(cause);
    }
}