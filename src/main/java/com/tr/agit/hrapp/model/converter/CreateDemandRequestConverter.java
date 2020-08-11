package com.tr.agit.hrapp.model.converter;

import com.tr.agit.hrapp.controller.request.CreateDemandRequest;
import com.tr.agit.hrapp.model.dto.DemandDto;
import com.tr.agit.hrapp.model.enums.DemandStatus;

import java.time.temporal.ChronoUnit;

public class CreateDemandRequestConverter {

    private CreateDemandRequestConverter() {
    }

    public static DemandDto convert(CreateDemandRequest createDemandRequest) {
        DemandDto demand = new DemandDto();

        demand.setType(createDemandRequest.getType());
        demand.setStartDate(createDemandRequest.getStartDate());
        demand.setEndDate(createDemandRequest.getEndDate());
        long totalDays = ChronoUnit.DAYS.between(demand.getStartDate(), demand.getEndDate());
        demand.setTotalDays(totalDays);
        demand.setStatus(DemandStatus.WAITINGFORAPPRROVAL);

        return demand;
    }
}