package com.tr.agit.hrapp.controller.request;

import com.tr.agit.hrapp.model.enums.DemandStatus;

import java.io.Serializable;

public class UpdateDemandStatusRequest implements Serializable {

    private static final long serialVersionUID = -2466938675821575275L;
    private DemandStatus status;

    public DemandStatus getStatus() {
        return status;
    }

    public void setStatus(DemandStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UpdateDemandStatusRequest{" +
                "status=" + status +
                '}';
    }
}
