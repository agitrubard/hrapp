package com.tr.agit.hrapp.model.exception;

public class MemberNotFoundException extends Exception{

    public MemberNotFoundException() {
        super("Member is not found or passive!");
    }

    public MemberNotFoundException(Throwable cause) {
        super(cause);
    }
}