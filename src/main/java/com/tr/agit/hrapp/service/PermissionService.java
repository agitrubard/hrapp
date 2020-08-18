package com.tr.agit.hrapp.service;

import com.tr.agit.hrapp.controller.request.CreatePermissionRequest;
import com.tr.agit.hrapp.controller.request.UpdatePermissionRequest;
import com.tr.agit.hrapp.controller.request.UpdatePermissionStatusRequest;
import com.tr.agit.hrapp.controller.response.GetPermissionResponse;
import com.tr.agit.hrapp.model.exception.MemberNotFoundException;
import com.tr.agit.hrapp.model.exception.PermissionNotFoundException;

import java.util.List;

public interface PermissionService {

    void create(long id, CreatePermissionRequest createPermissionRequest) throws MemberNotFoundException;

    void update(long memberId, UpdatePermissionRequest updatePermissionRequest, long permissionId) throws MemberNotFoundException, PermissionNotFoundException;

    void updateStatus(long memberId, UpdatePermissionStatusRequest updatePermissionStatusRequest, long permissionId) throws MemberNotFoundException, PermissionNotFoundException;

    List<GetPermissionResponse> getPermissionsByMemberId(long id) throws MemberNotFoundException;
}