package com.tr.agit.hrapp.service.impl;

import com.tr.agit.hrapp.member.MemberDTO;
import com.tr.agit.hrapp.model.MemberEntity;
import com.tr.agit.hrapp.repository.MemberRepository;
import com.tr.agit.hrapp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

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

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(MemberDTO memberDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(memberDTO.getEmail());
        message.setSubject("Welcome to HRApp");
        message.setText("Your Password is : " + memberDTO.getPassword());

        javaMailSender.send(message);
        System.out.println("Your Message : " + message.getText() + "\n" + "To : " + memberDTO.getEmail());
    }
}
