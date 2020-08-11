package com.tr.agit.hrapp.model.converter;

import com.tr.agit.hrapp.controller.request.UpdateDemandStatusRequest;
import com.tr.agit.hrapp.model.dto.DemandDto;

public class UpdateDemandStatusRequestConverter {

    private UpdateDemandStatusRequestConverter() {

    }

    public static DemandDto convert(UpdateDemandStatusRequest updateDemandStatusRequest) {
        DemandDto demand = new DemandDto();

        demand.setStatus(updateDemandStatusRequest.getStatus());

        return demand;
    }
}