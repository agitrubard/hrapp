package com.tr.agit.hrapp.controller.request;

import com.tr.agit.hrapp.model.enums.ResignStatus;

import java.io.Serializable;

public class UpdateResignStatusRequest implements Serializable {

    private static final long serialVersionUID = -4525389853790720024L;
    private ResignStatus status;

    public ResignStatus getStatus() {
        return status;
    }

    public void setStatus(ResignStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UpdateResignStatusRequest{" +
                "status=" + status +
                '}';
    }
}