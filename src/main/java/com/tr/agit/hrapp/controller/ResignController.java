package com.tr.agit.hrapp.controller;

import com.tr.agit.hrapp.controller.request.UpdateResignStatusRequest;
import com.tr.agit.hrapp.controller.response.GetResignedResponse;
import com.tr.agit.hrapp.model.exception.MemberNotFoundException;
import com.tr.agit.hrapp.model.exception.ResignAlreadyExistException;
import com.tr.agit.hrapp.model.exception.ResignNotFoundException;
import com.tr.agit.hrapp.service.ResignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class ResignController {

    @Autowired
    ResignService resignService;

    @PostMapping(value = "/{id}/resign")
    public void create(@PathVariable long id) throws ResignAlreadyExistException, MemberNotFoundException {
        resignService.create(id);
    }

    @PutMapping(value = "/{id}/resign-status")
    public void updateStatus(@PathVariable long id, @RequestBody UpdateResignStatusRequest updateResignStatusRequest) throws ResignNotFoundException, MemberNotFoundException {
        resignService.updateStatus(id, updateResignStatusRequest);
    }

    @GetMapping(value = "/resigned")
    public List<GetResignedResponse> getResignedMembers() {
        return resignService.getResignedMembers();
    }
}