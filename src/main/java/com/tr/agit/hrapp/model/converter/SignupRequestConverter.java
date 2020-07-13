package com.tr.agit.hrapp.model.converter;

import com.tr.agit.hrapp.controller.request.SignupRequest;
import com.tr.agit.hrapp.model.dto.MemberDto;

public class SignupRequestConverter {

    private SignupRequestConverter() {
    }

    public static MemberDto convert(SignupRequest signupRequest) {
        MemberDto member = new MemberDto();
        member.setEmail(signupRequest.getEmail());
        member.setName(signupRequest.getName());
        member.setSurname(signupRequest.getSurname());
        return member;
    }
}
