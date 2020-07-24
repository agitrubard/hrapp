package com.tr.agit.hrapp.controller.response;

import com.tr.agit.hrapp.model.enums.RoleType;

public class GetRoleResponse {

    private String username;
    private RoleType type;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GetRoleResponse{" +
                "username='" + username + '\'' +
                ", type=" + type +
                '}';
    }
}
