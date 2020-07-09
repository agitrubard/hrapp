package com.tr.agit.hrapp.service;

import com.tr.agit.hrapp.controller.request.LoginRequest;
import com.tr.agit.hrapp.controller.request.PasswordChangeRequest;
import com.tr.agit.hrapp.controller.request.SignupRequest;
import com.tr.agit.hrapp.model.dto.MemberDto;
import com.tr.agit.hrapp.model.entity.MemberEntity;

import java.util.List;

public interface MemberService {

    void addMembers(List<MemberDto> memberDtos);
    void createMember(SignupRequest signupRequest);
    void loginMember(LoginRequest loginRequest);
    void passwordChange(PasswordChangeRequest passwordChangeRequest);
    void updateMember(long id, SignupRequest signupRequest);
    void deleteMember(long id);
    void sendEmail(MemberDto member, int tempPassword);
    List<MemberEntity> getMembers();
    MemberEntity getMemberById(long id);
}