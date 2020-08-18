package com.tr.agit.hrapp.controller.request;

import com.tr.agit.hrapp.model.enums.PermissionStatus;

import java.io.Serializable;

public class UpdatePermissionStatusRequest implements Serializable {

    private static final long serialVersionUID = -2466938675821575275L;
    private PermissionStatus status;

    public PermissionStatus getStatus() {
        return status;
    }

    public void setStatus(PermissionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UpdatePermissionStatusRequest{" +
                "status=" + status +
                '}';
    }
}