package com.tr.agit.hrapp.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tr.agit.hrapp.model.enums.PermissionType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UpdatePermissionRequest implements Serializable {

    private static final long serialVersionUID = -4241921580209031521L;
    private PermissionType type;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate endDate;
}