package com.tr.agit.hrapp.model.dto;

import com.tr.agit.hrapp.model.enums.RoleType;

public class RoleDto {

    private RoleType type;

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                ", type=" + type +
                '}';
    }
}