package com.tr.agit.hrapp.model.exception;

public class ResignNotFoundException extends Exception {

    private static final long serialVersionUID = -285748868106647838L;

    public ResignNotFoundException() {
        super("Member's resign is not found or answered!");
    }

    public ResignNotFoundException(Throwable cause) {
        super(cause);
    }
}