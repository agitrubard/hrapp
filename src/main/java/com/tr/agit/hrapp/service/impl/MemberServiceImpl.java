package com.tr.agit.hrapp.service.impl;

import com.tr.agit.hrapp.member.MemberDTO;
import com.tr.agit.hrapp.model.MemberEntity;
import com.tr.agit.hrapp.repository.MemberRepository;
import com.tr.agit.hrapp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Random;

@Service
public class MemberServiceImpl implements MemberService {
    static Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder();

    private static int generatePassword() {
        Random random = new Random();
        int password = 100000 + random.nextInt(900000);
        return password;
    }

    private static String passwordEncoder(String password) {
        return encoder.encode(password);
    }

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
        entity.setPassword(passwordEncoder(memberDTO.getPassword()));
        entity.setEmail(memberDTO.getEmail());

        memberRepository.save(entity);
        System.out.println(memberDTO.getName() + "'s Member Added to Database.");
        sendEmail(memberDTO);
    }

    @Override
    public void addMembers(Iterable<MemberDTO> memberDTO) {
        for (MemberDTO member : memberDTO) {
            MemberEntity entity = new MemberEntity();
            entity.setName(member.getName());
            entity.setSurname(member.getSurname());
            entity.setPassword(passwordEncoder(member.getPassword()));
            entity.setEmail(member.getEmail());

            memberRepository.save(entity);
            System.out.println(member.getName() + "'s Member Added to Database.");
            sendEmail(member);
        }
    }

    @Override
    public void updateMember(long id, MemberDTO memberDTO) {
        MemberEntity entity = getMemberById(id);
        entity.setName(memberDTO.getName());
        entity.setSurname(memberDTO.getSurname());
        entity.setPassword(passwordEncoder(memberDTO.getPassword()));
        entity.setEmail(memberDTO.getEmail());

        memberRepository.save(entity);
        System.out.println(memberDTO.getName() + "'s Member Information Updated to Database.");
    }

    @Override
    public void deleteMember(long id) {
        MemberEntity entity = getMemberById(id);

        memberRepository.deleteById(id);
        System.out.println(entity.getName() + "'s Member Deleted From Database.");
    }

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(MemberDTO memberDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("demiragitrubar@gmail.com");
        message.setTo(memberDTO.getEmail());
        message.setSubject("Welcome to HRApp");
        message.setText("Your Temporary Password is : " + generatePassword());

        javaMailSender.send(message);
        System.out.println("Temporary Password e-Mail was Sent.\n");
    }

    @Override
    public Iterable<MemberEntity> getMembers() {
        return memberRepository.findAll();
    }

    @Override
    public MemberEntity getMemberById(long id) {
        return memberRepository.findById(id).orElse(null);
    }
}

