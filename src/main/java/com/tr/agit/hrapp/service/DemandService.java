package com.tr.agit.hrapp.service;

import com.tr.agit.hrapp.controller.request.CreateDemandRequest;
import com.tr.agit.hrapp.controller.request.UpdateDemandRequest;
import com.tr.agit.hrapp.controller.request.UpdateDemandStatusRequest;
import com.tr.agit.hrapp.controller.response.GetDemandResponse;

import java.util.List;

public interface DemandService {
    void create(long id, CreateDemandRequest createDemandRequest);

    void update(long memberId, UpdateDemandRequest updateDemandRequest, long demandId);

    void updateStatus(long memberId, UpdateDemandStatusRequest updateDemandStatusRequest, long demandId);

    List<GetDemandResponse> getByMemberId(long id);
}