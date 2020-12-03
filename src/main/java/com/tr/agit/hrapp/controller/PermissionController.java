package com.tr.agit.hrapp.controller;

import com.tr.agit.hrapp.controller.endpoint.MemberControllerEndpoint;
import com.tr.agit.hrapp.controller.endpoint.PermissionControllerEndpoint;
import com.tr.agit.hrapp.controller.request.CreatePermissionRequest;
import com.tr.agit.hrapp.controller.request.UpdatePermissionRequest;
import com.tr.agit.hrapp.controller.request.UpdatePermissionStatusRequest;
import com.tr.agit.hrapp.controller.response.GetPermissionResponse;
import com.tr.agit.hrapp.model.exception.MemberNotFoundException;
import com.tr.agit.hrapp.model.exception.PermissionNotFoundException;
import com.tr.agit.hrapp.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(MemberControllerEndpoint.MEMBER)
public class PermissionController {

    PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping(value = PermissionControllerEndpoint.CREATE_PERMISSION_BY_MEMBER_ID)
    public void create(@PathVariable long id, @RequestBody CreatePermissionRequest createPermissionRequest) throws MemberNotFoundException {
        permissionService.create(id, createPermissionRequest);
    }

    @PutMapping(value = PermissionControllerEndpoint.UPDATE_PERMISSION_BY_MEMBER_ID_AND_PERMISSION_ID)
    public void update(@PathVariable long memberId, @RequestBody UpdatePermissionRequest updatePermissionRequest, @PathVariable long permissionId) throws MemberNotFoundException, PermissionNotFoundException {
        permissionService.update(memberId, updatePermissionRequest, permissionId);
    }

    @PutMapping(value = PermissionControllerEndpoint.UPDATE_PERMISSION_STATUS_BY_MEMBER_ID_AND_PERMISSION_ID)
    public void updateStatus(@PathVariable long memberId, @RequestBody UpdatePermissionStatusRequest updatePermissionStatusRequest, @PathVariable long permissionId) throws MemberNotFoundException, PermissionNotFoundException {
        permissionService.updateStatus(memberId, updatePermissionStatusRequest, permissionId);
    }

    @GetMapping(value = PermissionControllerEndpoint.GET_PERMISSIONS_BY_MEMBER_ID)
    public List<GetPermissionResponse> getPermissionsByMemberId(@PathVariable long id) throws MemberNotFoundException {
        return permissionService.getPermissionsByMemberId(id);
    }
}