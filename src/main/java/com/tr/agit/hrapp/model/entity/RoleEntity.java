package com.tr.agit.hrapp.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @JoinColumn(name = "member_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(targetEntity = MemberEntity.class)
    private MemberEntity memberId;

    @Column(name = "title")
    private String title;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", title='" + title + '\'' +
                '}';
    }
}
