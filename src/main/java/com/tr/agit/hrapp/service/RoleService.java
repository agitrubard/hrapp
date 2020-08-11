package com.tr.agit.hrapp.service;

import com.tr.agit.hrapp.controller.request.AddRoleRequest;
import com.tr.agit.hrapp.controller.response.GetRoleResponse;

import java.util.List;

public interface RoleService {

    void add(long id, AddRoleRequest addRoleRequest);

    List<GetRoleResponse> get();

    GetRoleResponse getByMemberId(long id);
}
