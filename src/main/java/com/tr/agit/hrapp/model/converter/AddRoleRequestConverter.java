package com.tr.agit.hrapp.model.converter;

import com.tr.agit.hrapp.controller.request.AddRoleRequest;
import com.tr.agit.hrapp.model.dto.RoleDto;

public class AddRoleRequestConverter {

    private AddRoleRequestConverter() {
    }

    public static RoleDto convert(AddRoleRequest addRoleRequest) {
        RoleDto role = new RoleDto();

        role.setType(addRoleRequest.getType());

        return role;
    }
}
