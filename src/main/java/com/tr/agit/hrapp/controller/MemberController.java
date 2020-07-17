package com.tr.agit.hrapp.controller;

import com.tr.agit.hrapp.controller.request.ChangePasswordRequest;
import com.tr.agit.hrapp.controller.request.LoginRequest;
import com.tr.agit.hrapp.controller.request.SignupRequest;
import com.tr.agit.hrapp.controller.request.UpdateRequest;
import com.tr.agit.hrapp.controller.response.GetMemberResponse;
import com.tr.agit.hrapp.model.dto.MemberDto;
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

    @PutMapping(value = "/password")
    public void passwordChange(@RequestBody ChangePasswordRequest changePasswordRequest) {
        memberService.changePassword(changePasswordRequest);
    }

    @PostMapping(value = "/add")
    public void add(@RequestBody List<MemberDto> memberDtos) {
        memberService.add(memberDtos);
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable long id, @RequestBody UpdateRequest updateRequest) {
        memberService.update(id, updateRequest);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable long id) {
        memberService.delete(id);
    }

    @GetMapping(value = "/")
    public List<GetMemberResponse> get() {
        return memberService.get();
    }

    @GetMapping(value = "/{id}")
    public GetMemberResponse getById(@PathVariable long id) {
        return memberService.getById(id);
    }
}
