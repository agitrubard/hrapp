package com.tr.agit.hrapp.model.converter;

import com.tr.agit.hrapp.controller.request.CreatePermissionRequest;
import com.tr.agit.hrapp.model.dto.PermissionDto;
import com.tr.agit.hrapp.model.enums.PermissionStatus;

import java.time.temporal.ChronoUnit;

public class CreatePermissionRequestConverter {

    private CreatePermissionRequestConverter() {
    }

    public static PermissionDto convert(CreatePermissionRequest createPermissionRequest) {
        PermissionDto permission = new PermissionDto();

        permission.setType(createPermissionRequest.getType());
        permission.setStartDate(createPermissionRequest.getStartDate());
        permission.setEndDate(createPermissionRequest.getEndDate());
        long totalDays = ChronoUnit.DAYS.between(permission.getStartDate(), permission.getEndDate());
        permission.setTotalDays(totalDays);
        permission.setStatus(PermissionStatus.WAITINGFORAPPRROVAL);

        return permission;
    }
}