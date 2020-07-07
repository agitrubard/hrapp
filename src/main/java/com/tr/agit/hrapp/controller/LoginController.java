package com.tr.agit.hrapp.controller;

import com.tr.agit.hrapp.member.MemberDTO;
import com.tr.agit.hrapp.model.MemberEntity;
import com.tr.agit.hrapp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class LoginController {
    @Autowired
    private MemberService memberService;

    @PostMapping(value = "/sign-up")
    public void signUp(@RequestBody MemberDTO memberDTO) {
        memberService.createMember(memberDTO);
    }

    @PostMapping(value = "/add-member")
    public String addMember(@RequestBody MemberDTO memberDTO) {
        memberService.addMember(memberDTO);
        return memberDTO.getName() + "' Member Added to Database.\nTemporary Password e-Mail was Sent.";
    }

    @PostMapping(value = "/add-members")
    public String addMembers(@RequestBody Iterable<MemberDTO> memberDTO) {
        memberService.addMembers(memberDTO);

        ArrayList<String> arrayList = new ArrayList<>();
        for (MemberDTO member : memberDTO) {
            arrayList.add(member.getName());
        }
        return arrayList + " Members Added to Database.\nTemporary Password e-Mail was Sent.";
    }

    @PostMapping(value = "/update-member/{id}")
    public String updateMember(@RequestBody MemberDTO memberDTO, @PathVariable long id) {
        memberService.updateMember(id, memberDTO);
        return memberService.getMemberById(id).getName() + "'s Member Information Update to Database.";
    }

    @DeleteMapping(value = "/delete-member/{id}")
    public String deleteMember(@PathVariable long id) {
        MemberEntity entity = getMemberById(id);
        memberService.deleteMember(id);
        return entity.getName() + "'s Member Deleted From Database.";
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
