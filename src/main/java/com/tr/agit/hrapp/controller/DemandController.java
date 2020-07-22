package com.tr.agit.hrapp.controller;

import com.tr.agit.hrapp.controller.request.CreateDemandRequest;
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
    public void createDemand(@PathVariable long id, @RequestBody CreateDemandRequest createDemandRequest) {
        demandService.create(id, createDemandRequest);
    }

    @GetMapping(value = "/{id}/demands")
    public List<GetDemandResponse> getDemands(@PathVariable long id) {
        return demandService.getById(id);
    }
}
