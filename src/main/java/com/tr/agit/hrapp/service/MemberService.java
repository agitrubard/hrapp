package com.tr.agit.hrapp.service;

import com.tr.agit.hrapp.member.MemberDTO;
import com.tr.agit.hrapp.model.MemberEntity;

public interface MemberService {

    void createMember(MemberDTO memberDTO);
    void addMember(MemberDTO memberDTO);
    void addMembers(Iterable<MemberDTO> memberDTO);
    void updateMember(long id, MemberDTO memberDTO);
    void deleteMember(long id);
    void sendEmail(MemberDTO memberDTO);
    Iterable<MemberEntity> getMembers();
    MemberEntity getMemberById(long id);
}
