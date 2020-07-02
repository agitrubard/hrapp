package com.tr.agit.hrapp.controller;

import com.tr.agit.hrapp.member.MemberDTO;
import com.tr.agit.hrapp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private MemberService memberService;

    @PostMapping(value = "/sign-up")
    public void signUp(@RequestBody MemberDTO memberDTO) {
        memberService.createMember(memberDTO);
    }

    @PostMapping(value = "/add-member")
    public void addMember(@RequestBody MemberDTO memberDTO) {
        memberService.addMember(memberDTO);
    }

    @PostMapping(value = "/send-email")
    public void sendEmail(@RequestBody MemberDTO memberDTO) {
        memberService.sendEmail(memberDTO);
    }
}
