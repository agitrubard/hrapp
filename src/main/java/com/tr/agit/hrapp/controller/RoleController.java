package com.tr.agit.hrapp.controller;

import com.tr.agit.hrapp.controller.request.AddRoleRequest;
import com.tr.agit.hrapp.controller.response.GetRoleResponse;
import com.tr.agit.hrapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping(value = "/{id}/add-role")
    public void add(@PathVariable long id, @RequestBody AddRoleRequest addRoleRequest) {
        roleService.add(id, addRoleRequest);
    }

    @GetMapping(value = "/{id}/role")
    public GetRoleResponse getRoleByMemberId(@PathVariable long id) {
        return roleService.getByMemberId(id);
    }

    @GetMapping(value = "/roles")
    public List<GetRoleResponse> getRoles() {
        return roleService.get();
    }
}