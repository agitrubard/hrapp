package com.tr.agit.hrapp.model.entity;

import com.tr.agit.hrapp.model.enums.DemandStatus;
import com.tr.agit.hrapp.model.enums.DemandType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "demands")
public class DemandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private long id;

    @JoinColumn(name = "member_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(targetEntity = MemberEntity.class)
    private MemberEntity memberId;

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private DemandType type;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "total_days", nullable = false)
    private long totalDays;

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private DemandStatus status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MemberEntity getMemberId() {
        return memberId;
    }

    public void setMemberId(MemberEntity memberId) {
        this.memberId = memberId;
    }

    public DemandType getType() {
        return type;
    }

    public void setType(DemandType type) {
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

    public DemandStatus getStatus() {
        return status;
    }

    public void setStatus(DemandStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DemandEntity{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", type=" + type +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalDays=" + totalDays +
                ", status=" + status +
                '}';
    }
}