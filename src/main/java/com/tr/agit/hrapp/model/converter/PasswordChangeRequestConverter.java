package com.tr.agit.hrapp.model.converter;

import com.tr.agit.hrapp.controller.request.PasswordChangeRequest;
import com.tr.agit.hrapp.model.dto.MemberDto;

public class PasswordChangeRequestConverter {

    private PasswordChangeRequestConverter() {
    }

    public static MemberDto convert(PasswordChangeRequest passwordChangeRequest) {
        MemberDto member = new MemberDto();
        member.setEmail(passwordChangeRequest.getEmail());
        member.setPassword(passwordChangeRequest.getPassword());
        member.setNewPassword(passwordChangeRequest.getNewPassword());
        member.setNewPasswordConfirm(passwordChangeRequest.getNewPasswordConfirm());
        return member;
    }
}
