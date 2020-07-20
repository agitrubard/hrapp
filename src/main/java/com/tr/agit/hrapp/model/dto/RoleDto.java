package com.tr.agit.hrapp.model.dto;

public class RoleDto {

    private long memberId;
    private String title;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "memberId=" + memberId +
                ", title='" + title + '\'' +
                '}';
    }
}
