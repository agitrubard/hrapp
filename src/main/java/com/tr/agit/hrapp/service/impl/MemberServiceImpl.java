package com.tr.agit.hrapp.service.impl;

import com.tr.agit.hrapp.member.MemberDTO;
import com.tr.agit.hrapp.model.MemberEntity;
import com.tr.agit.hrapp.repository.MemberRepository;
import com.tr.agit.hrapp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Override
    public void createMember(MemberDTO memberDTO) {
        System.out.println(memberDTO);
    }

    @Autowired
    MemberRepository memberRepository;

    @Override
    public void addMember(MemberDTO memberDTO) {
        MemberEntity entity = new MemberEntity();
        entity.setName(memberDTO.getName());
        entity.setSurname(memberDTO.getSurname());
        entity.setPassword(memberDTO.getPassword());
        entity.setEmail(memberDTO.getEmail());

        memberRepository.save(entity);
        System.out.println(memberDTO.getName() + "'s User Added to Database.");
    }
}
