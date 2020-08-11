package com.tr.agit.hrapp.controller.request;

import com.tr.agit.hrapp.model.enums.RoleType;

import java.io.Serializable;

public class AddRoleRequest implements Serializable {

    private static final long serialVersionUID = 4521310546106390989L;
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