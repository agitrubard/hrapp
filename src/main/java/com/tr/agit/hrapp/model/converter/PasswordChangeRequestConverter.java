package com.tr.agit.hrapp.model.converter;

import com.tr.agit.hrapp.controller.request.ChangePasswordRequest;
import com.tr.agit.hrapp.model.dto.MemberDto;

public class PasswordChangeRequestConverter {

    private PasswordChangeRequestConverter() {
    }

    public static MemberDto convert(ChangePasswordRequest changePasswordRequest) {
        MemberDto member = new MemberDto();
        member.setEmail(changePasswordRequest.getEmail());
        member.setPassword(changePasswordRequest.getPassword());
        member.setNewPassword(changePasswordRequest.getNewPassword());
        return member;
    }
}
