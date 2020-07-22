package com.tr.agit.hrapp.model.converter;

import com.tr.agit.hrapp.controller.request.UpdateRequest;
import com.tr.agit.hrapp.model.dto.MemberDto;

public class UpdateRequestConverter {

    private UpdateRequestConverter() {

    }

    public static MemberDto convert(UpdateRequest updateRequest) {
        MemberDto member = new MemberDto();
        member.setEmail(updateRequest.getEmail());
        member.setUsername(updateRequest.getUsername());
        member.setName(updateRequest.getName());
        member.setSurname(updateRequest.getSurname());
        return member;
    }
}
