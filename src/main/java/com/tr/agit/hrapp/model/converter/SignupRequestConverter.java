package com.tr.agit.hrapp.model.converter;

import com.tr.agit.hrapp.controller.request.SignupRequest;
import com.tr.agit.hrapp.model.dto.MemberDto;
import com.tr.agit.hrapp.model.enums.MemberStatus;

public class SignupRequestConverter {

    private SignupRequestConverter() {
    }

    public static MemberDto convert(SignupRequest signupRequest) {
        MemberDto member = new MemberDto();

        member.setName(signupRequest.getName());
        member.setSurname(signupRequest.getSurname());
        member.setEmail(signupRequest.getEmail());
        member.setBirthdate(signupRequest.getBirthdate());
        member.setStatus(MemberStatus.ACTIVE);

        return member;
    }
}