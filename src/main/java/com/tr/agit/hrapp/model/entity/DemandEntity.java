package com.tr.agit.hrapp.model.entity;

import com.tr.agit.hrapp.model.enums.DemandStatus;
import com.tr.agit.hrapp.model.enums.DemandType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "demands")
public class DemandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @JoinColumn(name = "member_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(targetEntity = MemberEntity.class)
    private MemberEntity memberId;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "total_time")
    private int totalTime;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private DemandType type;

    @Column(name = "status")
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public DemandType getType() {
        return type;
    }

    public void setType(DemandType type) {
        this.type = type;
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
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalTime=" + totalTime +
                ", type=" + type +
                ", status=" + status +
                '}';
    }
}