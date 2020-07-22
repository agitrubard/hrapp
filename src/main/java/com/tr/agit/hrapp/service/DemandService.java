package com.tr.agit.hrapp.service;

import com.tr.agit.hrapp.controller.request.CreateDemandRequest;
import com.tr.agit.hrapp.controller.response.GetDemandResponse;

import java.util.List;

public interface DemandService {

    void create(long id, CreateDemandRequest createDemandRequest);

    List<GetDemandResponse> getById(long id);
}
