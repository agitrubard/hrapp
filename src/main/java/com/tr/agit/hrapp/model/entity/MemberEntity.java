package com.tr.agit.hrapp.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private long id;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "temppassword",nullable = false)
    private String tempPassword;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "surname",nullable = false)
    private String surname;

    public MemberEntity() {
    }

    public MemberEntity(MemberEntity memberEntity) {
        this.id = memberEntity.getId();
        this.email = memberEntity.getEmail();
        this.password = memberEntity.getPassword();
        this.tempPassword = memberEntity.getTempPassword();
        this.name = memberEntity.getName();
        this.surname = memberEntity.getSurname();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTempPassword() {
        return tempPassword;
    }

    public void setTempPassword(String tempPassword) {
        this.tempPassword = tempPassword;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberEntity)) return false;
        MemberEntity entity = (MemberEntity) o;
        return getId() == entity.getId() &&
                Objects.equals(getEmail(), entity.getEmail()) &&
                Objects.equals(getPassword(), entity.getPassword()) &&
                Objects.equals(getTempPassword(), entity.getTempPassword()) &&
                Objects.equals(getName(), entity.getName()) &&
                Objects.equals(getSurname(), entity.getSurname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getPassword(), getTempPassword(), getName(), getSurname());
    }

    @Override
    public String toString() {
        return "MemberEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", tempPassword='" + tempPassword + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
