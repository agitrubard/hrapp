package com.tr.agit.hrapp.controller;

import com.tr.agit.hrapp.controller.endpoint.MemberControllerEndpoint;
import com.tr.agit.hrapp.controller.request.*;
import com.tr.agit.hrapp.controller.response.GetMemberResponse;
import com.tr.agit.hrapp.model.exception.MemberAlreadyExistsException;
import com.tr.agit.hrapp.model.exception.MemberNotFoundException;
import com.tr.agit.hrapp.model.exception.PasswordNotCorrectException;
import com.tr.agit.hrapp.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(MemberControllerEndpoint.MEMBER)
public class MemberController {

    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(value = MemberControllerEndpoint.SIGN_UP)
    public void signUp(@RequestBody SignupRequest signupRequest) throws MemberAlreadyExistsException {
        memberService.create(signupRequest);
    }

    @PostMapping(value = MemberControllerEndpoint.LOG_IN)
    public void logIn(@RequestBody LoginRequest loginRequest) throws MemberNotFoundException, PasswordNotCorrectException {
        memberService.login(loginRequest);
    }

    @PutMapping(value = MemberControllerEndpoint.PASSWORD)
    public void passwordChange(@RequestBody ChangePasswordRequest changePasswordRequest) throws Exception {
        memberService.changePassword(changePasswordRequest);
    }

    @PutMapping(value = MemberControllerEndpoint.ID)
    public void update(@PathVariable long id, @RequestBody UpdateMemberRequest updateMemberRequest) throws MemberNotFoundException {
        memberService.update(id, updateMemberRequest);
    }

    @DeleteMapping(value = MemberControllerEndpoint.ID)
    public void delete(@PathVariable long id) throws MemberNotFoundException {
        memberService.delete(id);
    }

    @GetMapping(value = MemberControllerEndpoint.SLASH)
    public List<GetMemberResponse> getMembers(@ModelAttribute PaginationRequest paginationRequest) {
        return memberService.getMembers(paginationRequest);
    }

    @GetMapping(value = MemberControllerEndpoint.ID)
    public GetMemberResponse getMemberById(@PathVariable long id) throws MemberNotFoundException {
        return memberService.getMemberById(id);
    }
}