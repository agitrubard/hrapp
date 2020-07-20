package com.tr.agit.hrapp.service;

import com.tr.agit.hrapp.controller.request.ChangePasswordRequest;
import com.tr.agit.hrapp.controller.request.LoginRequest;
import com.tr.agit.hrapp.controller.request.SignupRequest;
import com.tr.agit.hrapp.controller.request.UpdateRequest;
import com.tr.agit.hrapp.controller.response.GetMemberResponse;
import com.tr.agit.hrapp.model.dto.MemberDto;
import com.tr.agit.hrapp.model.entity.MemberEntity;

import java.util.List;

public interface MemberService {

    void create(SignupRequest signupRequest) throws Exception;
    void sendEmail(MemberEntity entity, String tempPassword);
    void login(LoginRequest loginRequest);
    void changePassword(ChangePasswordRequest changePasswordRequest) throws Exception;
    void add(List<MemberDto> memberDtos) throws Exception;
    void update(long id, UpdateRequest updateRequest);
    void delete(long id);
    List<GetMemberResponse>  get();
    GetMemberResponse getById(long id);
}