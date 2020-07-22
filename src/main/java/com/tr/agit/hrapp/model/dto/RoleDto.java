package com.tr.agit.hrapp.model.dto;

import com.tr.agit.hrapp.model.enums.RoleType;

public class RoleDto {

    private long memberId;
    private RoleType type;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "memberId=" + memberId +
                ", type=" + type +
                '}';
    }
}
