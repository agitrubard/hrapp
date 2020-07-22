package com.tr.agit.hrapp.controller;

import com.tr.agit.hrapp.controller.request.AddRoleRequest;
import com.tr.agit.hrapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping(value = "/{id}/add-role")
    public void add(@PathVariable long id, @RequestBody AddRoleRequest addRoleRequest) {
        roleService.add(id, addRoleRequest);
    }
}
