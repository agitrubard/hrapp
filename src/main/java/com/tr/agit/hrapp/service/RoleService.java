package com.tr.agit.hrapp.service;

import com.tr.agit.hrapp.controller.request.AddRoleRequest;
import com.tr.agit.hrapp.controller.response.GetRoleResponse;
import com.tr.agit.hrapp.model.exception.MemberNotFoundException;

import java.util.List;

public interface RoleService {

    void add(long id, AddRoleRequest addRoleRequest) throws MemberNotFoundException;

    List<GetRoleResponse> get();

    GetRoleResponse getByMemberId(long id) throws MemberNotFoundException;
}