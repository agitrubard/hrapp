package com.tr.agit.hrapp.model.exception;

public class DemandNotFoundException extends Exception{

    public DemandNotFoundException() {
        super("Demand is not found!");
    }

    public DemandNotFoundException(Throwable cause) {
        super(cause);
    }
}