package com.tr.agit.hrapp.controller;

import com.tr.agit.hrapp.controller.request.*;
import com.tr.agit.hrapp.controller.response.GetMemberResponse;
import com.tr.agit.hrapp.model.exception.MemberAlreadyExistsException;
import com.tr.agit.hrapp.model.exception.MemberNotFoundException;
import com.tr.agit.hrapp.model.exception.PasswordNotCorrectException;
import com.tr.agit.hrapp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @PostMapping(value = "/sign-up")
    public void signUp(@RequestBody SignupRequest signupRequest) throws MemberAlreadyExistsException {
        memberService.create(signupRequest);
    }

    @PostMapping(value = "/log-in")
    public void logIn(@RequestBody LoginRequest loginRequest) throws MemberNotFoundException, PasswordNotCorrectException {
        memberService.login(loginRequest);
    }

    @PutMapping(value = "/password")
    public void passwordChange(@RequestBody ChangePasswordRequest changePasswordRequest) throws Exception {
        memberService.changePassword(changePasswordRequest);
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable long id, @RequestBody UpdateMemberRequest updateMemberRequest) throws MemberNotFoundException {
        memberService.update(id, updateMemberRequest);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable long id) throws MemberNotFoundException {
        memberService.delete(id);
    }

    @GetMapping(value = "/")
    public List<GetMemberResponse> getMembers(@ModelAttribute PaginationRequest paginationRequest) {
        return memberService.getMembers(paginationRequest);
    }

    @GetMapping(value = "/{id}")
    public GetMemberResponse getMemberById(@PathVariable long id) throws MemberNotFoundException {
        return memberService.getMemberById(id);
    }
}