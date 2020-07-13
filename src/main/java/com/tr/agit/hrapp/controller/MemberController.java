package com.tr.agit.hrapp.controller;

import com.tr.agit.hrapp.controller.request.ChangePasswordRequest;
import com.tr.agit.hrapp.controller.request.LoginRequest;
import com.tr.agit.hrapp.controller.request.SignupRequest;
import com.tr.agit.hrapp.model.dto.MemberDto;
import com.tr.agit.hrapp.model.entity.MemberEntity;
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
    public void signUp(@RequestBody SignupRequest signupRequest) {
        memberService.create(signupRequest);
    }

    @PostMapping(value = "/log-in")
    public void logIn(@RequestBody LoginRequest loginRequest) {
        memberService.login(loginRequest);
    }

    @PutMapping(value = "/password/{id}")
    public void passwordChange(@PathVariable long id, @RequestBody ChangePasswordRequest changePasswordRequest) {
        memberService.changePassword(id, changePasswordRequest);
    }

    @PostMapping(value = "/add")
    public void addMembers(@RequestBody List<MemberDto> memberDtos) {
        memberService.add(memberDtos);
    }

    @PutMapping(value = "/{id}")
    public void updateMember(@PathVariable long id, @RequestBody SignupRequest signupRequest) {
        memberService.update(id, signupRequest);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteMember(@PathVariable long id) {
        memberService.delete(id);
    }

    @GetMapping(value = "/")
    public Iterable<MemberEntity> getMembers() {
        return memberService.get();
    }

    @GetMapping(value = "/{id}")
    public MemberEntity getMemberById(@PathVariable long id) {
        return memberService.getById(id);
    }
}
