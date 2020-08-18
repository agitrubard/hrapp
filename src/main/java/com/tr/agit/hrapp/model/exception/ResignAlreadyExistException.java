package com.tr.agit.hrapp.model.exception;

public class ResignAlreadyExistException extends Exception {

    private static final long serialVersionUID = -8783234292571729047L;

    public ResignAlreadyExistException() {
        super("Member's resign is already exist!");
    }

    public ResignAlreadyExistException(Throwable cause) {
        super(cause);
    }
}