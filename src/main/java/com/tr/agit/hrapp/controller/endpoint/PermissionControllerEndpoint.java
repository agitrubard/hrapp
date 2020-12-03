package com.tr.agit.hrapp.controller.endpoint;

public class PermissionControllerEndpoint {

    public static final String CREATE_PERMISSION_BY_MEMBER_ID = "/{id}/permission";
    public static final String UPDATE_PERMISSION_BY_MEMBER_ID_AND_PERMISSION_ID = "/{memberId}/permission/{permissionId}";
    public static final String UPDATE_PERMISSION_STATUS_BY_MEMBER_ID_AND_PERMISSION_ID = "/{memberId}/permission-status/{permissionId}";
    public static final String GET_PERMISSIONS_BY_MEMBER_ID = "/{id}/permissions";
}