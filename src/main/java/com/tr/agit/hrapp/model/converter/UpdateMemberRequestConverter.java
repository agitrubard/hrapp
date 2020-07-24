package com.tr.agit.hrapp.model.converter;

import com.tr.agit.hrapp.controller.request.UpdateMemberRequest;
import com.tr.agit.hrapp.model.dto.MemberDto;

public class UpdateMemberRequestConverter {

    private UpdateMemberRequestConverter() {

    }

    public static MemberDto convert(UpdateMemberRequest updateMemberRequest) {
        MemberDto member = new MemberDto();

        member.setEmail(updateMemberRequest.getEmail());
        member.setUsername(updateMemberRequest.getUsername());
        member.setName(updateMemberRequest.getName());
        member.setSurname(updateMemberRequest.getSurname());

        return member;
    }
}
