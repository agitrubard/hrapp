package com.tr.agit.hrapp.service.impl;

import com.tr.agit.hrapp.controller.request.ChangePasswordRequest;
import com.tr.agit.hrapp.controller.request.LoginRequest;
import com.tr.agit.hrapp.controller.request.SignupRequest;
import com.tr.agit.hrapp.controller.request.UpdateRequest;
import com.tr.agit.hrapp.controller.response.GetMemberResponse;
import com.tr.agit.hrapp.model.converter.SignupRequestConverter;
import com.tr.agit.hrapp.model.converter.UpdateRequestConverter;
import com.tr.agit.hrapp.model.dto.MemberDto;
import com.tr.agit.hrapp.model.entity.MemberEntity;
import com.tr.agit.hrapp.repository.MemberRepository;
import com.tr.agit.hrapp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    MemberRepository memberRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public void create(SignupRequest signupRequest) {
        MemberDto member = SignupRequestConverter.convert(signupRequest);
        save(member);
    }

    @Override
    public void login(LoginRequest loginRequest) {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findByUsername(loginRequest.getUsername());

        if (memberEntityOptional.isPresent()) {
            boolean control = encoder.matches(loginRequest.getPassword(), memberEntityOptional.get().getPassword());
            if (control) {
                System.out.println("Successful.");
            }
        }
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findByUsername(changePasswordRequest.getUsername());

        if (memberEntityOptional.isPresent()) {
            boolean control = encoder.matches(changePasswordRequest.getPassword(), memberEntityOptional.get().getPassword());
            if (control) {
                String newPassword = passwordEncoder(changePasswordRequest.getNewPassword());
                memberEntityOptional.get().setPassword(newPassword);
                memberRepository.save(memberEntityOptional.get());
            }
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
        List<MemberEntity> memberEntities = memberRepository.findAll();
        return getResponses(memberEntities);
    }

    private List<GetMemberResponse> getResponses(List<MemberEntity> memberEntities) {
        return memberEntities.stream()
                .map(this::getResponse)
                .collect(Collectors.toList());
    }

    @Override
    public GetMemberResponse getById(long id) {
        if (memberRepository.existsById(id)) {
            MemberEntity memberEntity = memberRepository.findById(id);
            return getResponse(memberEntity);
        } else {
            return null;
        }
    }

    private GetMemberResponse getResponse(MemberEntity memberEntity) {
        GetMemberResponse getMemberResponse = new GetMemberResponse();
        getMemberResponse.setEmail(memberEntity.getEmail());
        getMemberResponse.setUsername(memberEntity.getUsername());
        getMemberResponse.setName(memberEntity.getName());
        getMemberResponse.setSurname(memberEntity.getSurname());

        return getMemberResponse;
    }

    private void save(MemberDto member) {
        MemberEntity entity = new MemberEntity();
        entity.setEmail(member.getEmail());
        String username = emailToUsername(member.getEmail());
        entity.setUsername(username);
        String tempPassword = String.valueOf(generatePassword());
        entity.setPassword(passwordEncoder(tempPassword));
        entity.setName(member.getName());
        entity.setSurname(member.getSurname());

        memberRepository.save(entity);
        sendEmail(entity, tempPassword);
    }

    private String emailToUsername(String email) {
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