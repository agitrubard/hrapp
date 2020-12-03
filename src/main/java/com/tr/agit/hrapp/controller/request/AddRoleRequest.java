package com.tr.agit.hrapp.controller.request;

import com.tr.agit.hrapp.model.enums.RoleType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class AddRoleRequest implements Serializable {

    private static final long serialVersionUID = 4521310546106390989L;
    private RoleType type;
}