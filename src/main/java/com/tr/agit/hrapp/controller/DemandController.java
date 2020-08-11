package com.tr.agit.hrapp.controller;

import com.tr.agit.hrapp.controller.request.CreateDemandRequest;
import com.tr.agit.hrapp.controller.request.UpdateDemandRequest;
import com.tr.agit.hrapp.controller.request.UpdateDemandStatusRequest;
import com.tr.agit.hrapp.controller.response.GetDemandResponse;
import com.tr.agit.hrapp.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class DemandController {

    @Autowired
    DemandService demandService;

    @PostMapping(value = "/{id}/create-demand")
    public void create(@PathVariable long id, @RequestBody CreateDemandRequest createDemandRequest) {
        demandService.create(id, createDemandRequest);
    }

    @PutMapping(value = "/{memberId}/demand/{demandId}")
    public void update(@PathVariable long memberId, @RequestBody UpdateDemandRequest updateDemandRequest, @PathVariable long demandId) {
        demandService.update(memberId, updateDemandRequest, demandId);
    }

    @PutMapping(value = "/{memberId}/demand-status/{demandId}")
    public void updateStatus(@PathVariable long memberId, @RequestBody UpdateDemandStatusRequest updateDemandStatusRequest, @PathVariable long demandId) {
        demandService.updateStatus(memberId, updateDemandStatusRequest, demandId);
    }

    @GetMapping(value = "/{id}/demands")
    public List<GetDemandResponse> getDemandsById(@PathVariable long id) {
        return demandService.getByMemberId(id);
    }
}