package com.tr.agit.hrapp.service;

import com.tr.agit.hrapp.controller.request.*;
import com.tr.agit.hrapp.controller.response.GetMemberResponse;
import com.tr.agit.hrapp.model.exception.MemberAlreadyExistsException;
import com.tr.agit.hrapp.model.exception.MemberNotFoundException;
import com.tr.agit.hrapp.model.exception.PasswordNotCorrectException;

import java.util.List;

public interface MemberService {

    void create(SignupRequest signupRequest) throws MemberAlreadyExistsException;

    void login(LoginRequest loginRequest) throws MemberNotFoundException, PasswordNotCorrectException;

    void changePassword(ChangePasswordRequest changePasswordRequest) throws Exception;

    void update(long id, UpdateMemberRequest updateMemberRequest) throws MemberNotFoundException;

    void delete(long id) throws MemberNotFoundException;

    List<GetMemberResponse> getMembers(PaginationRequest paginationRequest);

    GetMemberResponse getMemberById(long id) throws MemberNotFoundException;
}