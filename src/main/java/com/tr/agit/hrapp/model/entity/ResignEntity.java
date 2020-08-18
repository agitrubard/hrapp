package com.tr.agit.hrapp.model.entity;

import com.tr.agit.hrapp.model.enums.ResignStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "resignations")
public class ResignEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private long id;

    @Column(name = "resign_date", nullable = false)
    private LocalDate resignDate;

    @Column(name = "total_working_days", nullable = false)
    private long totalWorkingDays;

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ResignStatus status;

    @JoinColumn(name = "member", referencedColumnName = "id", nullable = false)
    @ManyToOne(targetEntity = MemberEntity.class)
    private MemberEntity member;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getResignDate() {
        return resignDate;
    }

    public void setResignDate(LocalDate resignDate) {
        this.resignDate = resignDate;
    }

    public long getTotalWorkingDays() {
        return totalWorkingDays;
    }

    public void setTotalWorkingDays(long totalWorkingDays) {
        this.totalWorkingDays = totalWorkingDays;
    }

    public ResignStatus getStatus() {
        return status;
    }

    public void setStatus(ResignStatus status) {
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
        return "ResignEntity{" +
                "id=" + id +
                ", resignDate=" + resignDate +
                ", totalWorkingDays=" + totalWorkingDays +
                ", status=" + status +
                ", member=" + member +
                '}';
    }
}