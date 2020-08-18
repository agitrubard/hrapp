package com.tr.agit.hrapp.model.entity;

import com.tr.agit.hrapp.model.enums.PermissionStatus;
import com.tr.agit.hrapp.model.enums.PermissionType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "permissions")
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private long id;

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PermissionType type;

    @Column(name = "start_working_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_working_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "total_working_days", nullable = false)
    private long totalDays;

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PermissionStatus status;

    @JoinColumn(name = "member", referencedColumnName = "id", nullable = false)
    @ManyToOne(targetEntity = MemberEntity.class)
    private MemberEntity member;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PermissionType getType() {
        return type;
    }

    public void setType(PermissionType type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public long getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(long totalDays) {
        this.totalDays = totalDays;
    }

    public PermissionStatus getStatus() {
        return status;
    }

    public void setStatus(PermissionStatus status) {
        this.status = status;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "PermissionEntity{" +
                "id=" + id +
                ", type=" + type +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalDays=" + totalDays +
                ", status=" + status +
                ", member=" + member +
                '}';
    }
}