package com.tr.agit.hrapp.controller;

import com.tr.agit.hrapp.controller.request.CreatePermissionRequest;
import com.tr.agit.hrapp.controller.request.UpdatePermissionRequest;
import com.tr.agit.hrapp.controller.request.UpdatePermissionStatusRequest;
import com.tr.agit.hrapp.controller.response.GetPermissionResponse;
import com.tr.agit.hrapp.model.exception.MemberNotFoundException;
import com.tr.agit.hrapp.model.exception.PermissionNotFoundException;
import com.tr.agit.hrapp.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @PostMapping(value = "/{id}/permission")
    public void create(@PathVariable long id, @RequestBody CreatePermissionRequest createPermissionRequest) throws MemberNotFoundException {
        permissionService.create(id, createPermissionRequest);
    }

    @PutMapping(value = "/{memberId}/permission/{permissionId}")
    public void update(@PathVariable long memberId, @RequestBody UpdatePermissionRequest updatePermissionRequest, @PathVariable long permissionId) throws MemberNotFoundException, PermissionNotFoundException {
        permissionService.update(memberId, updatePermissionRequest, permissionId);
    }

    @PutMapping(value = "/{memberId}/permission-status/{permissionId}")
    public void updateStatus(@PathVariable long memberId, @RequestBody UpdatePermissionStatusRequest updatePermissionStatusRequest, @PathVariable long permissionId) throws MemberNotFoundException, PermissionNotFoundException {
        permissionService.updateStatus(memberId, updatePermissionStatusRequest, permissionId);
    }

    @GetMapping(value = "/{id}/permissions")
    public List<GetPermissionResponse> getPermissionsByMemberId(@PathVariable long id) throws MemberNotFoundException {
        return permissionService.getPermissionsByMemberId(id);
    }
}