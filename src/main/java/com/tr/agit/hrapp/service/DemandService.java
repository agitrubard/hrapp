package com.tr.agit.hrapp.service;

import com.tr.agit.hrapp.controller.request.CreateDemandRequest;
import com.tr.agit.hrapp.controller.request.UpdateDemandRequest;
import com.tr.agit.hrapp.controller.request.UpdateDemandStatusRequest;
import com.tr.agit.hrapp.controller.response.GetDemandResponse;
import com.tr.agit.hrapp.model.exception.DemandNotFoundException;
import com.tr.agit.hrapp.model.exception.MemberNotFoundException;

import java.util.List;

public interface DemandService {

    void create(long id, CreateDemandRequest createDemandRequest) throws MemberNotFoundException;

    void update(long memberId, UpdateDemandRequest updateDemandRequest, long demandId) throws MemberNotFoundException, DemandNotFoundException;

    void updateStatus(long memberId, UpdateDemandStatusRequest updateDemandStatusRequest, long demandId) throws MemberNotFoundException, DemandNotFoundException;

    List<GetDemandResponse> getByMemberId(long id) throws MemberNotFoundException;
}