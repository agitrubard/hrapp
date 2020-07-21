package com.tr.agit.hrapp.model.entity;

import com.tr.agit.hrapp.model.enums.MemberStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "members")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private long id;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "surname",nullable = false)
    private String surname;

    @Column(name = "status",nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MemberStatus memberStatus;

    @OneToMany(mappedBy = "memberId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DemandEntity> demands;

    public MemberEntity() {
    }

    public MemberEntity(long id, String email, String username, String password, String name, String surname, MemberStatus memberStatus, DemandEntity demandId) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.memberStatus = memberStatus;
    }

    public MemberEntity(MemberEntity member) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public MemberStatus getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

    @Override
    public String toString() {
        return "MemberEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", memberStatus=" + memberStatus +
                '}';
    }
}
