package com.tr.agit.hrapp.model.dto;

import com.tr.agit.hrapp.model.enums.PermissionStatus;
import com.tr.agit.hrapp.model.enums.PermissionType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class PermissionDto implements Serializable {

    private static final long serialVersionUID = -7996772191488623437L;
    private PermissionType type;
    private LocalDate startDate;
    private LocalDate endDate;
    private long totalDays;
    private PermissionStatus status;
}