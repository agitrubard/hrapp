package com.tr.agit.hrapp.service.impl;

import com.tr.agit.hrapp.controller.request.LoginRequest;
import com.tr.agit.hrapp.controller.request.PasswordChangeRequest;
import com.tr.agit.hrapp.controller.request.SignupRequest;
import com.tr.agit.hrapp.model.converter.LoginRequestConverter;
import com.tr.agit.hrapp.model.converter.PasswordChangeRequestConverter;
import com.tr.agit.hrapp.model.converter.SignupRequestConverter;
import com.tr.agit.hrapp.model.dto.MemberDto;
import com.tr.agit.hrapp.model.entity.MemberEntity;
import com.tr.agit.hrapp.repository.MemberRepository;
import com.tr.agit.hrapp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    MemberRepository memberRepository;

    private Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder();
    private int tempPassword;

    @Override
    public void createMember(SignupRequest signupRequest) {
        MemberDto member = SignupRequestConverter.convert(signupRequest);
        MemberEntity entity = new MemberEntity();
        entity.setEmail(member.getEmail());
        entity.setPassword(passwordEncoder(member.getPassword()));
        tempPassword = generatePassword();
        entity.setTempPassword(String.valueOf(tempPassword));
        entity.setName(member.getName());
        entity.setSurname(member.getSurname());

        memberRepository.save(entity);
        sendEmail(member, tempPassword);
    }

    @Override
    public void loginMember(LoginRequest loginRequest) {
        MemberDto member = LoginRequestConverter.convert(loginRequest);

        // Login control
    }

    @Override
    public void passwordChange(PasswordChangeRequest passwordChangeRequest) {
        MemberDto member = PasswordChangeRequestConverter.convert(passwordChangeRequest);
        MemberEntity entity = new MemberEntity();

        boolean correct = true; // Password Control
        boolean control = member.getNewPassword().equals(member.getNewPasswordConfirm());
        if (correct) {
            if (control) {
                entity.setPassword(member.getNewPassword());
            }
        }
    }

    @Override
    public void addMembers(List<MemberDto> memberDtos) {
        for (MemberDto member : memberDtos) {
            MemberEntity entity = new MemberEntity();
            entity.setEmail(member.getEmail());
            entity.setPassword(passwordEncoder(member.getPassword()));
            tempPassword = generatePassword();
            entity.setTempPassword(String.valueOf(tempPassword));
            entity.setName(member.getName());
            entity.setSurname(member.getSurname());

            memberRepository.save(entity);
            sendEmail(member, tempPassword);
        }
    }

    @Override
    public void updateMember(long id, SignupRequest signupRequest) {
        MemberDto member = SignupRequestConverter.convert(signupRequest);
        MemberEntity entity = getMemberById(id);
        entity.setName(member.getName());
        entity.setSurname(member.getSurname());
        entity.setPassword(passwordEncoder(member.getPassword()));
        entity.setEmail(member.getEmail());

        memberRepository.save(entity);
    }

    @Override
    public void deleteMember(long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public void sendEmail(MemberDto member, int tempPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("demiragitrubar@gmail.com");
        message.setTo(member.getEmail());
        message.setSubject("Welcome to HRApp");
        message.setText("Your Temporary Password is : " + tempPassword);

        javaMailSender.send(message);
    }

    @Override
    public List<MemberEntity> getMembers() {
        return (List<MemberEntity>) memberRepository.findAll();
    }

    @Override
    public MemberEntity getMemberById(long id) {
        return memberRepository.findById(id).orElse(null);
    }

    private int generatePassword() {
        Random random = new Random();
        int password = 100000 + random.nextInt(900000);
        return password;
    }

    private String passwordEncoder(String password) {
        return encoder.encode(password);
    }
}

