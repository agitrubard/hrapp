package com.tr.agit.hrapp.model.exception;

public class MemberNotFoundException extends Exception {

    private static final long serialVersionUID = 3911118191144091574L;

    public MemberNotFoundException() {
        super("Member is not found or passive!");
    }

    public MemberNotFoundException(Throwable cause) {
        super(cause);
    }
}