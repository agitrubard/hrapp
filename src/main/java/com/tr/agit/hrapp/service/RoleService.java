package com.tr.agit.hrapp.service;

import com.tr.agit.hrapp.controller.request.AddRoleRequest;
import com.tr.agit.hrapp.controller.response.GetRoleResponse;
import com.tr.agit.hrapp.model.exception.MemberNotFoundException;
import com.tr.agit.hrapp.model.exception.RoleNotFoundException;

import java.util.List;

public interface RoleService {

    void add(long id, AddRoleRequest addRoleRequest) throws MemberNotFoundException;

    List<GetRoleResponse> getMembersRole();

    GetRoleResponse getRoleByMemberId(long id) throws MemberNotFoundException, RoleNotFoundException;
}