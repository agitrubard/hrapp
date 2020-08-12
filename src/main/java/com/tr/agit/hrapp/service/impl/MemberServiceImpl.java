package com.tr.agit.hrapp.service.impl;

import com.tr.agit.hrapp.controller.request.ChangePasswordRequest;
import com.tr.agit.hrapp.controller.request.LoginRequest;
import com.tr.agit.hrapp.controller.request.SignupRequest;
import com.tr.agit.hrapp.controller.request.UpdateMemberRequest;
import com.tr.agit.hrapp.controller.response.GetMemberResponse;
import com.tr.agit.hrapp.model.converter.ChangePasswordRequestConverter;
import com.tr.agit.hrapp.model.converter.LoginRequestConverter;
import com.tr.agit.hrapp.model.converter.SignupRequestConverter;
import com.tr.agit.hrapp.model.converter.UpdateMemberRequestConverter;
import com.tr.agit.hrapp.model.dto.MemberDto;
import com.tr.agit.hrapp.model.entity.MemberEntity;
import com.tr.agit.hrapp.model.enums.MemberStatus;
import com.tr.agit.hrapp.model.exception.MemberAlreadyExistsException;
import com.tr.agit.hrapp.model.exception.MemberNotFoundException;
import com.tr.agit.hrapp.model.exception.PasswordNotCorrectException;
import com.tr.agit.hrapp.repository.MemberRepository;
import com.tr.agit.hrapp.repository.RoleRepository;
import com.tr.agit.hrapp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Autowired
    RoleRepository roleRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public void create(SignupRequest signupRequest) throws MemberAlreadyExistsException {
        MemberDto member = SignupRequestConverter.convert(signupRequest);
        save(member);
    }

    @Override
    public void login(LoginRequest loginRequest) throws MemberNotFoundException, PasswordNotCorrectException {
        MemberDto member = LoginRequestConverter.convert(loginRequest);
        Optional<MemberEntity> memberEntityOptional = memberRepository.findByUsername(member.getUsername());

        boolean memberIsPresent = memberEntityOptional.isPresent();
        boolean memberStatusIsActive = (memberEntityOptional.get().getStatus() == MemberStatus.ACTIVE);

        if (memberIsPresent && memberStatusIsActive) {
            loginPasswordControl(loginRequest, memberEntityOptional);
        } else {
            throw new MemberNotFoundException();
        }
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) throws MemberNotFoundException, PasswordNotCorrectException {
        MemberDto member = ChangePasswordRequestConverter.convert(changePasswordRequest);

        Optional<MemberEntity> memberEntityOptional = memberRepository.findByUsername(member.getUsername());

        boolean memberIsPresent = memberEntityOptional.isPresent();
        boolean memberStatusIsActive = memberEntityOptional.get().getStatus() == MemberStatus.ACTIVE;

        if (memberIsPresent && memberStatusIsActive) {
            changePasswordControl(member, memberEntityOptional);
        } else {
            throw new MemberNotFoundException();
        }
    }

    @Override
    public void update(long id, UpdateMemberRequest updateMemberRequest) throws MemberNotFoundException {
        MemberDto member = UpdateMemberRequestConverter.convert(updateMemberRequest);

        Optional<MemberEntity> memberEntityOptional = memberRepository.findById(id);

        boolean memberIsPresent = memberEntityOptional.isPresent();
        boolean memberStatusIsActive = memberEntityOptional.get().getStatus() == MemberStatus.ACTIVE;

        if (memberIsPresent && memberStatusIsActive) {
            updateMember(member, memberEntityOptional);
        } else {
            throw new MemberNotFoundException();
        }
    }

    @Override
    public void delete(long id) throws MemberNotFoundException {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findById(id);

        boolean memberIsPresent = memberEntityOptional.isPresent();
        boolean memberStatusIsActive = memberEntityOptional.get().getStatus() == MemberStatus.ACTIVE;

        if (memberIsPresent && memberStatusIsActive) {
            deleteMember(memberEntityOptional);
        } else {
            throw new MemberNotFoundException();
        }
    }

    @Override
    public List<GetMemberResponse> get() {
        List<MemberEntity> memberEntities = memberRepository.findAll();

        return getResponses(memberEntities);
    }

    @Override
    public GetMemberResponse getById(long id) throws MemberNotFoundException {
        if (memberRepository.existsById(id)) {
            Optional<MemberEntity> memberEntity = memberRepository.findById(id);

            return getResponse(memberEntity.get());
        } else {
            throw new MemberNotFoundException();
        }
    }

    @Override
    public void sendPersonalInformationMessage(MemberEntity memberEntity, String tempPassword) {
        String to = memberEntity.getEmail();
        String subject = "Welcome to HRApp";
        String text = "Username : " + memberEntity.getUsername() + "\nTemporary Password : " + tempPassword;

        sendMail(to, subject, text);
    }

    @Override
    @Scheduled(cron = "0 0 9 * * *", zone = "Europe/Istanbul")
    public void sendBirthdayMessage() {
        List<MemberEntity> memberEntities = memberRepository.findAllByBirthdate(LocalDate.now());

        for (MemberEntity member : memberEntities) {
            String to = member.getEmail();
            String subject = "Happy Birthday!";
            String text = "Happy birthday to you " + member.getName() + "!";

            sendMail(to, subject, text);
        }
    }

    private void save(MemberDto member) throws MemberAlreadyExistsException {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findByEmail(member.getEmail());

        if (!(memberEntityOptional.isPresent())) {
            saveMember(member);
        } else {
            throw new MemberAlreadyExistsException();
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
        memberEntity.setBirthdate(member.getBirthdate());
        memberEntity.setStatus(member.getStatus());

        memberRepository.save(memberEntity);

        sendPersonalInformationMessage(memberEntity, tempPassword);
    }

    private void changePasswordControl(MemberDto member, Optional<MemberEntity> memberEntityOptional) throws PasswordNotCorrectException {
        boolean passwordControl = encoder.matches(member.getPassword(), memberEntityOptional.get().getPassword());

        if (passwordControl) {
            String newPassword = passwordEncoder(member.getNewPassword());
            memberEntityOptional.get().setPassword(newPassword);

            memberRepository.save(memberEntityOptional.get());
        } else {
            throw new PasswordNotCorrectException();
        }
    }

    private void loginPasswordControl(LoginRequest loginRequest, Optional<MemberEntity> memberEntityOptional) throws PasswordNotCorrectException {
        boolean passwordControl = encoder.matches(loginRequest.getPassword(), memberEntityOptional.get().getPassword());

        if (!passwordControl) {
            throw new PasswordNotCorrectException();
        }
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

        getMemberResponse.setName(memberEntity.getName());
        getMemberResponse.setSurname(memberEntity.getSurname());
        getMemberResponse.setEmail(memberEntity.getEmail());
        getMemberResponse.setUsername(memberEntity.getUsername());
        getMemberResponse.setBirthdate(memberEntity.getBirthdate());
        getMemberResponse.setStatus(memberEntity.getStatus());

        return getMemberResponse;
    }

    private List<GetMemberResponse> getResponses(List<MemberEntity> memberEntities) {
        return Optional.of(memberEntities.stream().map(this::getResponse).collect(Collectors.toList())).orElse(null);
    }

    private void sendMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("admin@hrapp.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
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