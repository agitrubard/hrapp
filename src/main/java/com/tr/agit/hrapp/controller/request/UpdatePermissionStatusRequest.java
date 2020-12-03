package com.tr.agit.hrapp.controller.request;

import com.tr.agit.hrapp.model.enums.PermissionStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class UpdatePermissionStatusRequest implements Serializable {

    private static final long serialVersionUID = -2466938675821575275L;
    private PermissionStatus status;
}