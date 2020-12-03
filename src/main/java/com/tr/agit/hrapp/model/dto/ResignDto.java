package com.tr.agit.hrapp.model.dto;

import com.tr.agit.hrapp.model.enums.ResignStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class ResignDto implements Serializable {

    private static final long serialVersionUID = -5264704565088109371L;
    private LocalDate resignDate;
    private long totalWorkingDays;
    private ResignStatus status;
}