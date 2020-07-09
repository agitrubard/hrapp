package com.tr.agit.hrapp.model.converter;

import com.tr.agit.hrapp.controller.request.LoginRequest;
import com.tr.agit.hrapp.model.dto.MemberDto;

public class LoginRequestConverter {

    private LoginRequestConverter() {
    }

    public static MemberDto convert(LoginRequest loginRequest) {
        MemberDto member = new MemberDto();
        member.setEmail(loginRequest.getEmail());
        member.setPassword(loginRequest.getPassword());
        member.setTempPassword(loginRequest.getTempPassword());
        return member;
    }
}
