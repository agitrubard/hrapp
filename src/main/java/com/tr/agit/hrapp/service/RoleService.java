package com.tr.agit.hrapp.service;

import com.tr.agit.hrapp.controller.request.AddRoleRequest;

public interface RoleService {

    void add(long id, AddRoleRequest addRoleRequest);
}
