package com.tr.agit.hrapp.controller.request;

import com.tr.agit.hrapp.model.enums.ResignStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class UpdateResignStatusRequest implements Serializable {

    private static final long serialVersionUID = -4525389853790720024L;
    private ResignStatus status;
}