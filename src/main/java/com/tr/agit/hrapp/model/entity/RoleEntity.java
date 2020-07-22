package com.tr.agit.hrapp.model.entity;

import com.tr.agit.hrapp.model.enums.RoleType;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @JoinColumn(name = "member_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(targetEntity = MemberEntity.class)
    private MemberEntity memberId;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private RoleType type;

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

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", type=" + type +
                '}';
    }
}
