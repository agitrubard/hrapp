package com.tr.agit.hrapp.controller;

import com.tr.agit.hrapp.controller.request.LoginRequest;
import com.tr.agit.hrapp.controller.request.PasswordChangeRequest;
import com.tr.agit.hrapp.controller.request.SignupRequest;
import com.tr.agit.hrapp.model.dto.MemberDto;
import com.tr.agit.hrapp.model.entity.MemberEntity;
import com.tr.agit.hrapp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private MemberService memberService;

    @PostMapping(value = "/sign-up")
    public void signUp(@RequestBody SignupRequest signupRequest) {
        memberService.createMember(signupRequest);
    }

    @PostMapping(value = "/log-in")
    public void logIn(@RequestBody LoginRequest loginRequest) {
        memberService.loginMember(loginRequest);
    }

    @PostMapping(value = "/password-change")
    public void addMembers(@RequestBody PasswordChangeRequest passwordChangeRequest) {
        memberService.passwordChange(passwordChangeRequest);
    }

    @PostMapping(value = "/add-members")
    public void addMembers(@RequestBody List<MemberDto> memberDtos) {
        memberService.addMembers(memberDtos);
    }

    @PostMapping(value = "/update-member/{id}")
    public void updateMember(@RequestBody SignupRequest signupRequest, @PathVariable long id) {
        memberService.updateMember(id, signupRequest);
    }

    @DeleteMapping(value = "/delete-member/{id}")
    public void deleteMember(@PathVariable long id) {
        memberService.deleteMember(id);
    }

    @GetMapping(value = "/get-members")
    public Iterable<MemberEntity> getMembers() {
        return memberService.getMembers();
    }

    @GetMapping(value = "/get-member-by-id/{id}")
    public MemberEntity getMemberById(@PathVariable long id) {
        return memberService.getMemberById(id);
    }
}
