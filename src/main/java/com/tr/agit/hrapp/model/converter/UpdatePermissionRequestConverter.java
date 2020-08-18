package com.tr.agit.hrapp.model.converter;

import com.tr.agit.hrapp.controller.request.UpdatePermissionRequest;
import com.tr.agit.hrapp.model.dto.PermissionDto;

import java.time.temporal.ChronoUnit;

public class UpdatePermissionRequestConverter {

    private UpdatePermissionRequestConverter() {
    }

    public static PermissionDto convert(UpdatePermissionRequest updatePermissionRequest) {
        PermissionDto permission = new PermissionDto();

        permission.setType(updatePermissionRequest.getType());
        permission.setStartDate(updatePermissionRequest.getStartDate());
        permission.setEndDate(updatePermissionRequest.getEndDate());
        long totalDays = ChronoUnit.DAYS.between(permission.getStartDate(), permission.getEndDate());
        permission.setTotalDays(totalDays);

        return permission;
    }
}