package com.tr.agit.hrapp.controller.request;

import com.tr.agit.hrapp.model.enums.RoleType;

public class AddRoleRequest {

    private RoleType type;

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AddRoleRequest{" +
                "type=" + type +
                '}';
    }
}
