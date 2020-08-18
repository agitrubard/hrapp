package com.tr.agit.hrapp.controller.response;

import com.tr.agit.hrapp.model.enums.RoleType;

public class GetRoleResponse {

    private long memberId;
    private String name;
    private String surname;
    private RoleType type;

    public long getMemberId(long id) {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
                "memberId=" + memberId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", type=" + type +
                '}';
    }
}