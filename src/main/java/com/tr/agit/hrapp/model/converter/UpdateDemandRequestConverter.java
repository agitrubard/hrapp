package com.tr.agit.hrapp.model.converter;

import com.tr.agit.hrapp.controller.request.UpdateDemandRequest;
import com.tr.agit.hrapp.model.dto.DemandDto;

import java.time.temporal.ChronoUnit;

public class UpdateDemandRequestConverter {

    private UpdateDemandRequestConverter() {
    }

    public static DemandDto convert(UpdateDemandRequest updateDemandRequest) {
        DemandDto demand = new DemandDto();

        demand.setType(updateDemandRequest.getType());
        demand.setStartDate(updateDemandRequest.getStartDate());
        demand.setEndDate(updateDemandRequest.getEndDate());
        long totalDays = ChronoUnit.DAYS.between(demand.getStartDate(), demand.getEndDate());
        demand.setTotalDays(totalDays);

        return demand;
    }
}