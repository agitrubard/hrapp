package com.tr.agit.hrapp.service;

import com.tr.agit.hrapp.controller.request.ChangePasswordRequest;
import com.tr.agit.hrapp.controller.request.LoginRequest;
import com.tr.agit.hrapp.controller.request.SignupRequest;
import com.tr.agit.hrapp.model.dto.MemberDto;
import com.tr.agit.hrapp.model.entity.MemberEntity;

import java.util.List;

public interface MemberService {

    void add(List<MemberDto> memberDtos);
    void create(SignupRequest signupRequest);
    void login(LoginRequest loginRequest);
    void changePassword(long id, ChangePasswordRequest changePasswordRequest);
    void update(long id, SignupRequest signupRequest);
    void delete(long id);
    void sendEmail(MemberDto member, int tempPassword);
    Iterable<MemberEntity> get();
    MemberEntity getById(long id);
}