package com.tr.agit.hrapp.model.dto;

import com.tr.agit.hrapp.model.enums.MemberStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class MemberDto implements Serializable {

    private static final long serialVersionUID = 5601565721402723456L;
    private String email;
    private String username;
    private LocalDate startWorkingDate;
    private MemberStatus status;
    private String password;
    private String newPassword;
    private String name;
    private String surname;
    private LocalDate birthdate;
}