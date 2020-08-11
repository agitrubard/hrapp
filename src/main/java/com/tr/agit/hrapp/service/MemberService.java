package com.tr.agit.hrapp.service;

import com.tr.agit.hrapp.controller.request.ChangePasswordRequest;
import com.tr.agit.hrapp.controller.request.LoginRequest;
import com.tr.agit.hrapp.controller.request.SignupRequest;
import com.tr.agit.hrapp.controller.request.UpdateMemberRequest;
import com.tr.agit.hrapp.controller.response.GetMemberResponse;
import com.tr.agit.hrapp.model.entity.MemberEntity;
import com.tr.agit.hrapp.model.exception.MemberNotFoundException;
import com.tr.agit.hrapp.model.exception.PasswordNotCorrectException;

import java.util.List;

public interface MemberService {

    void create(SignupRequest signupRequest) throws Exception;

    void sendEmail(MemberEntity memberEntity, String tempPassword);

    void login(LoginRequest loginRequest) throws MemberNotFoundException, PasswordNotCorrectException;

    void changePassword(ChangePasswordRequest changePasswordRequest) throws Exception;

    void update(long id, UpdateMemberRequest updateMemberRequest) throws MemberNotFoundException;

    void delete(long id) throws MemberNotFoundException;

    List<GetMemberResponse> get();

    GetMemberResponse getById(long id) throws MemberNotFoundException;
}