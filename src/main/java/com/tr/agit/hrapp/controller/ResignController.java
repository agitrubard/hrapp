package com.tr.agit.hrapp.controller;

import com.tr.agit.hrapp.controller.endpoint.MemberControllerEndpoint;
import com.tr.agit.hrapp.controller.endpoint.ResignControllerEndpoint;
import com.tr.agit.hrapp.controller.request.UpdateResignStatusRequest;
import com.tr.agit.hrapp.controller.response.GetResignedResponse;
import com.tr.agit.hrapp.model.exception.MemberNotFoundException;
import com.tr.agit.hrapp.model.exception.ResignAlreadyExistException;
import com.tr.agit.hrapp.model.exception.ResignNotFoundException;
import com.tr.agit.hrapp.service.ResignService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(MemberControllerEndpoint.MEMBER)
public class ResignController {

    ResignService resignService;

    public ResignController(ResignService resignService) {
        this.resignService = resignService;
    }

    @PostMapping(value = ResignControllerEndpoint.CREATE_RESIGN_BY_MEMBER_ID)
    public void create(@PathVariable long id) throws ResignAlreadyExistException, MemberNotFoundException {
        resignService.create(id);
    }

    @PutMapping(value = ResignControllerEndpoint.UPDATE_RESIGN_STATUS_BY_MEMBER_ID)
    public void updateStatus(@PathVariable long id, @RequestBody UpdateResignStatusRequest updateResignStatusRequest) throws ResignNotFoundException, MemberNotFoundException {
        resignService.updateStatus(id, updateResignStatusRequest);
    }

    @GetMapping(value = ResignControllerEndpoint.GET_RESIGNED)
    public List<GetResignedResponse> getResignedMembers() {
        return resignService.getResignedMembers();
    }
}