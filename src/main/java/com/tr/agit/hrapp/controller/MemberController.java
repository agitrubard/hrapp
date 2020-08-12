package com.tr.agit.hrapp.controller;

import com.tr.agit.hrapp.controller.request.ChangePasswordRequest;
import com.tr.agit.hrapp.controller.request.LoginRequest;
import com.tr.agit.hrapp.controller.request.SignupRequest;
import com.tr.agit.hrapp.controller.request.UpdateMemberRequest;
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

    @GetMapping(value = "/")//pagination -> page : 0 - limit 10
    public List<GetMemberResponse> getMembers() {
        return memberService.get();
    }

    @GetMapping(value = "/{id}")
    public GetMemberResponse getMemberById(@PathVariable long id) throws MemberNotFoundException {
        return memberService.getById(id);
    }
}