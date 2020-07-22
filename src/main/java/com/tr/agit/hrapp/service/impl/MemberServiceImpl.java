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
import com.tr.agit.hrapp.model.enums.MemberStatus;
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
    public void create(SignupRequest signupRequest) throws Exception {
        MemberDto member = SignupRequestConverter.convert(signupRequest);
        save(member);
    }

    @Override
    public void login(LoginRequest loginRequest) {
        MemberDto member = LoginRequestConverter.convert(loginRequest);
        Optional<MemberEntity> memberEntityOptional = memberRepository.findByUsername(member.getUsername());

        if (memberEntityOptional.isPresent() && memberEntityOptional.get().getStatus() == MemberStatus.ACTIVE) {
            boolean control = encoder.matches(loginRequest.getPassword(), memberEntityOptional.get().getPassword());
            if (control) {
                System.out.println("Successful.");
            }
        }
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) throws Exception {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findByUsername(changePasswordRequest.getUsername());

        if (memberEntityOptional.isPresent()) {
            boolean control = encoder.matches(changePasswordRequest.getPassword(), memberEntityOptional.get().getPassword());
            if (control) {
                String newPassword = passwordEncoder(changePasswordRequest.getNewPassword());
                memberEntityOptional.get().setPassword(newPassword);
                memberRepository.save(memberEntityOptional.get());
            } else {
                throw new Exception("Password is not correct!");
            }
        } else {
            throw new NullPointerException("Member is not found or deleted!");
        }
    }

    @Override
    public void sendEmail(MemberEntity memberEntity, String tempPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("admin@hrapp.com");
        message.setTo(memberEntity.getEmail());
        message.setSubject("Welcome to HRApp");
        message.setText("Username : " + memberEntity.getUsername() + "\nTemporary Password : " + tempPassword);

        javaMailSender.send(message);
    }

    @Override
    public void update(long id, UpdateRequest updateRequest) {
        MemberDto member = UpdateRequestConverter.convert(updateRequest);
        Optional<MemberEntity> memberEntityOptional = memberRepository.findById(id);
        if (memberEntityOptional.isPresent() && memberEntityOptional.get().getStatus() == MemberStatus.ACTIVE) {
            updateMember(member, memberEntityOptional);
        } else {
            throw new NullPointerException("Member is not found or deleted!");
        }
    }

    @Override
    public void delete(long id) {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findById(id);
        if (memberEntityOptional.isPresent() && memberEntityOptional.get().getStatus() == MemberStatus.ACTIVE) {
            deleteMember(memberEntityOptional);
        } else {
            throw new NullPointerException("Member is not found or deleted!");
        }
    }

    @Override
    public List<GetMemberResponse> get() {
        List<MemberEntity> memberEntities = memberRepository.findAll();
        return getResponses(memberEntities);
    }

    @Override
    public GetMemberResponse getById(long id) {
        if (memberRepository.existsById(id)) {
            Optional<MemberEntity> memberEntity = memberRepository.findById(id);
            return getResponse(memberEntity.get());
        } else {
            return null;
        }
    }

    private void save(MemberDto member) throws Exception {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findByEmail(member.getEmail());

        if (!(memberEntityOptional.isPresent())) {
            saveMember(member);
        } else {
            throw new Exception("Member is already exists!");
        }
    }

    private void saveMember(MemberDto member) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setEmail(member.getEmail());
        String username = emailToUsername(member.getEmail());
        memberEntity.setUsername(username);
        String tempPassword = String.valueOf(generatePassword());
        memberEntity.setPassword(passwordEncoder(tempPassword));
        memberEntity.setName(member.getName());
        memberEntity.setSurname(member.getSurname());
        memberEntity.setStatus(member.getStatus());

        memberRepository.save(memberEntity);
        sendEmail(memberEntity, tempPassword);
    }

    private void updateMember(MemberDto member, Optional<MemberEntity> memberEntityOptional) {
        memberEntityOptional.get().setEmail(member.getEmail());
        memberEntityOptional.get().setName(member.getName());
        memberEntityOptional.get().setSurname(member.getSurname());

        memberRepository.save(memberEntityOptional.get());
    }

    private void deleteMember(Optional<MemberEntity> memberEntityOptional) {
        memberEntityOptional.get().setStatus(MemberStatus.PASSIVE);
        memberRepository.save(memberEntityOptional.get());
    }

    private GetMemberResponse getResponse(MemberEntity memberEntity) {
        GetMemberResponse getMemberResponse = new GetMemberResponse();
        getMemberResponse.setEmail(memberEntity.getEmail());
        getMemberResponse.setUsername(memberEntity.getUsername());
        getMemberResponse.setName(memberEntity.getName());
        getMemberResponse.setSurname(memberEntity.getSurname());
        getMemberResponse.setStatus(memberEntity.getStatus());

        return getMemberResponse;
    }

    private List<GetMemberResponse> getResponses(List<MemberEntity> memberEntities) {
        return memberEntities.stream()
                .map(this::getResponse)
                .collect(Collectors.toList());
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