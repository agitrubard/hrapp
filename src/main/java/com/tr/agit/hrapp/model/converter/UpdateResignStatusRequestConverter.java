package com.tr.agit.hrapp.model.converter;

import com.tr.agit.hrapp.controller.request.UpdateResignStatusRequest;
import com.tr.agit.hrapp.model.dto.ResignDto;

public class UpdateResignStatusRequestConverter {

    private UpdateResignStatusRequestConverter() {

    }

    public static ResignDto convert(UpdateResignStatusRequest updateResignStatusRequest) {
        ResignDto resign = new ResignDto();

        resign.setStatus(updateResignStatusRequest.getStatus());

        return resign;
    }
}