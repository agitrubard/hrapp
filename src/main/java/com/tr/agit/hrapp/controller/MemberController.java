package com.tr.agit.hrapp.controller;

import com.tr.agit.hrapp.controller.request.ChangePasswordRequest;
import com.tr.agit.hrapp.controller.request.LoginRequest;
import com.tr.agit.hrapp.controller.request.SignupRequest;
import com.tr.agit.hrapp.controller.request.UpdateRequest;
import com.tr.agit.hrapp.controller.response.GetMemberResponse;
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
    public void signUp(@RequestBody SignupRequest signupRequest) throws Exception {
        memberService.create(signupRequest);
    }

    @PostMapping(value = "/log-in")
    public void logIn(@RequestBody LoginRequest loginRequest) {
        memberService.login(loginRequest);
    }

    @PutMapping(value = "/password")
    public void passwordChange(@RequestBody ChangePasswordRequest changePasswordRequest) throws Exception {
        memberService.changePassword(changePasswordRequest);
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
    public List<GetMemberResponse> getMembers() {
        return memberService.get();
    }

    @GetMapping(value = "/{id}")
    public GetMemberResponse getMemberById(@PathVariable long id) {
        return memberService.getById(id);
    }
}
