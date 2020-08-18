package com.tr.agit.hrapp.model.converter;

import com.tr.agit.hrapp.controller.request.UpdatePermissionStatusRequest;
import com.tr.agit.hrapp.model.dto.PermissionDto;

public class UpdatePermissionStatusRequestConverter {

    private UpdatePermissionStatusRequestConverter() {

    }

    public static PermissionDto convert(UpdatePermissionStatusRequest updatePermissionStatusRequest) {
        PermissionDto permission = new PermissionDto();

        permission.setStatus(updatePermissionStatusRequest.getStatus());

        return permission;
    }
}