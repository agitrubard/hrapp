package com.tr.agit.hrapp.model.converter;

import com.tr.agit.hrapp.controller.request.SignupRequest;
import com.tr.agit.hrapp.model.dto.MemberDto;

public class AddMembersRequestConverter {

    private AddMembersRequestConverter() {
    }

    public static MemberDto convert(SignupRequest signupRequest) {
        MemberDto member = new MemberDto();
        member.setEmail(signupRequest.getEmail());
        member.setName(signupRequest.getName());
        member.setPassword(signupRequest.getPassword());
        member.setSurname(signupRequest.getSurname());
        return member;
    }
}
