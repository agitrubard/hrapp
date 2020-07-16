package com.tr.agit.hrapp.service.impl;

import com.tr.agit.hrapp.controller.request.ChangePasswordRequest;
import com.tr.agit.hrapp.controller.request.LoginRequest;
import com.tr.agit.hrapp.controller.request.SignupRequest;
import com.tr.agit.hrapp.controller.request.UpdateRequest;
import com.tr.agit.hrapp.controller.response.GetMemberResponse;
import com.tr.agit.hrapp.model.converter.LoginRequestConverter;
import com.tr.agit.hrapp.model.converter.SignupRequestConverter;
import com.tr.agit.hrapp.model.converter.UpdateRequestConverter;
import com.tr.agit.hrapp.model.dto.MemberDto;
import com.tr.agit.hrapp.model.entity.MemberEntity;
import com.tr.agit.hrapp.repository.GetMemberRepository;
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

    @Autowired
    GetMemberRepository getMemberRepository;

    private final Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder();

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
    public void sendEmail(MemberEntity entity, String tempPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("admin@hrapp.com");
        message.setTo(entity.getEmail());
        message.setSubject("Welcome to HRApp");
        message.setText("Username : " + entity.getUsername() + "\nTemporary Password : " + tempPassword);

        javaMailSender.send(message);
    }

    @Override
    public void add(List<MemberDto> memberDtos) {
        for (MemberDto member : memberDtos) {
            save(member);
        }
    }

    @Override
    public void update(long id, UpdateRequest updateRequest) {
        MemberDto member = UpdateRequestConverter.convert(updateRequest);
        MemberEntity memberEntity = memberRepository.findById(id);
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
    public List<GetMemberResponse> get() {
        return getMemberRepository.findAll();
    }

    @Override
    public GetMemberResponse getById(long id) {
        if (getMemberRepository.existsById(id)) {
            return getMemberRepository.findById(id);
        } else {
            return null;
        }
    }

    private void save(MemberDto member) {
        MemberEntity entity = new MemberEntity();
        entity.setEmail(member.getEmail());
        String username = emailToUsername(member.getEmail());
        entity.setUsername(username);
        String tempPassword = String.valueOf(generatePassword());
        //entity.setPassword(passwordEncoder(tempPassword));
        entity.setPassword(tempPassword);
        entity.setName(member.getName());
        entity.setSurname(member.getSurname());

        memberRepository.save(entity);
        sendEmail(entity, tempPassword);
    }

    private String emailToUsername(String email){
        String[] str = email.split("@");
        return str[0];
    }

    private int generatePassword() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }

    private String passwordEncoder(String password) {
        return encoder.encode(password);
    }
}

