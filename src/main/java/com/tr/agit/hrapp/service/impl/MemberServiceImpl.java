package com.tr.agit.hrapp.service.impl;

import com.tr.agit.hrapp.controller.request.ChangePasswordRequest;
import com.tr.agit.hrapp.controller.request.LoginRequest;
import com.tr.agit.hrapp.controller.request.SignupRequest;
import com.tr.agit.hrapp.model.converter.LoginRequestConverter;
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
import java.util.Optional;
import java.util.Random;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    MemberRepository memberRepository;

    private final Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder();
    private int tempPassword;

    @Override
    public void create(SignupRequest signupRequest) {
        MemberDto member = SignupRequestConverter.convert(signupRequest);
        save(member);
    }

    @Override
    public void login(LoginRequest loginRequest) {
        MemberDto member = LoginRequestConverter.convert(loginRequest);

        // Login control
    }

    @Override
    public void changePassword(long id, ChangePasswordRequest changePasswordRequest) {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findByIdAndPassword(id, changePasswordRequest.getPassword());

        //şifrelenmiş parola ile karşılaştırma

        if (memberEntityOptional.isPresent()) {
            memberEntityOptional.get().setPassword(changePasswordRequest.getNewPassword());
            memberRepository.save(memberEntityOptional.get());
            System.out.println("Password is changed.");
        } else {
            System.out.println("Password is not correct!");
        }
    }

    @Override
    public void sendEmail(MemberDto member, int tempPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("admin@hrapp.com");
        message.setTo(member.getEmail());
        message.setSubject("Welcome to HRApp");
        message.setText("Your Temporary Password is : " + tempPassword);

        javaMailSender.send(message);
    }

    @Override
    public void add(List<MemberDto> memberDtos) {
        for (MemberDto member : memberDtos) {
            save(member);
        }
    }

    @Override
    public void update(long id, SignupRequest signupRequest) {
        MemberDto member = SignupRequestConverter.convert(signupRequest);
        MemberEntity memberEntity = getById(id);
        memberEntity.setEmail(member.getEmail());
        memberEntity.setName(member.getName());
        memberEntity.setSurname(member.getSurname());

        memberRepository.save(memberEntity);
    }

    @Override
    public void delete(long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public Iterable<MemberEntity> get() {
        //GetMemberResponse ve List dönecek
        return memberRepository.findAll();
    }

    @Override
    public MemberEntity getById(long id) {
        //GetMemberResponse dönecek
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

    private void save(MemberDto member) {
        MemberEntity entity = new MemberEntity();
        entity.setEmail(member.getEmail());
        //entity.setPassword(passwordEncoder(member.getPassword()));
        entity.setPassword(member.getPassword());
        tempPassword = generatePassword();
        entity.setTempPassword(String.valueOf(tempPassword));
        entity.setName(member.getName());
        entity.setSurname(member.getSurname());

        memberRepository.save(entity);
        sendEmail(member, tempPassword);
    }
}

