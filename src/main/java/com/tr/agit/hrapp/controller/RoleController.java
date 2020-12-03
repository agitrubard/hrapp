package com.tr.agit.hrapp.controller;

import com.tr.agit.hrapp.controller.endpoint.MemberControllerEndpoint;
import com.tr.agit.hrapp.controller.endpoint.RoleControllerEndpoint;
import com.tr.agit.hrapp.controller.request.AddRoleRequest;
import com.tr.agit.hrapp.controller.response.GetRoleResponse;
import com.tr.agit.hrapp.model.exception.MemberNotFoundException;
import com.tr.agit.hrapp.model.exception.RoleNotFoundException;
import com.tr.agit.hrapp.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(MemberControllerEndpoint.MEMBER)
public class RoleController {

    RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(value = RoleControllerEndpoint.ROLE_BY_MEMBER_ID)
    public void add(@PathVariable long id, @RequestBody AddRoleRequest addRoleRequest) throws MemberNotFoundException {
        roleService.add(id, addRoleRequest);
    }

    @GetMapping(value = RoleControllerEndpoint.ROLE_BY_MEMBER_ID)
    public GetRoleResponse getRoleByMemberId(@PathVariable long id) throws MemberNotFoundException, RoleNotFoundException {
        return roleService.getRoleByMemberId(id);
    }

    @GetMapping(value = RoleControllerEndpoint.GET_ROLES)
    public List<GetRoleResponse> getMembersRole() {
        return roleService.getMembersRole();
    }
}