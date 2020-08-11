package com.tr.agit.hrapp.model.exception;

public class PasswordNotCorrectException extends Exception {

    public PasswordNotCorrectException() {
        super("Password is not correct!");
    }

    public PasswordNotCorrectException(Throwable cause) {
        super(cause);
    }
}