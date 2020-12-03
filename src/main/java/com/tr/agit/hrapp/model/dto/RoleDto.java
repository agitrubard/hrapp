package com.tr.agit.hrapp.model.dto;

import com.tr.agit.hrapp.model.enums.RoleType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoleDto {

    private RoleType type;
}