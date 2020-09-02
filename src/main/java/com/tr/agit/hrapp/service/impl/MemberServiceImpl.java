package com.tr.agit.hrapp.service.impl;

import com.tr.agit.hrapp.controller.request.*;
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
import com.tr.agit.hrapp.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    MemberRepository memberRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    NotificationService notificationService;

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
    public List<GetMemberResponse> getMembers(PaginationRequest paginationRequest) {
        Page<MemberEntity> memberEntities = memberRepository.findAll(
                PageRequest.of(paginationRequest.getPage(), paginationRequest.getLimit(), null));

        return getResponses(memberEntities.getContent());
    }

    @Override
    public GetMemberResponse getMemberById(long id) throws MemberNotFoundException {

        boolean memberIsPresent = memberRepository.existsById(id);

        if (memberIsPresent) {
            Optional<MemberEntity> memberEntity = memberRepository.findById(id);

            return getResponse(memberEntity.get());
        } else {
            throw new MemberNotFoundException();
        }
    }

    private void save(MemberDto member) throws MemberAlreadyExistsException {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findByEmail(member.getEmail());

        boolean memberIsPresent = memberEntityOptional.isPresent();

        if (!(memberIsPresent)) {
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
        memberEntity.setStartWorkingDate(LocalDate.now());
        String tempPassword = String.valueOf(generatePassword());
        memberEntity.setPassword(passwordEncoder(tempPassword));
        memberEntity.setName(member.getName());
        memberEntity.setSurname(member.getSurname());
        memberEntity.setBirthdate(member.getBirthdate());
        memberEntity.setStatus(member.getStatus());

        memberRepository.save(memberEntity);

        notificationService.sendPersonalInformationMessage(memberEntity, tempPassword);
    }

    private String emailToUsername(String email) {
        String[] str = email.split("@");
        return str[0];
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
        memberEntityOptional.get().setUsername(member.getUsername());
        memberEntityOptional.get().setName(member.getName());
        memberEntityOptional.get().setSurname(member.getSurname());
        memberEntityOptional.get().setBirthdate(member.getBirthdate());

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
        getMemberResponse.setStartWorkingDate(memberEntity.getStartWorkingDate());
        getMemberResponse.setStatus(memberEntity.getStatus());
        getMemberResponse.setName(memberEntity.getName());
        getMemberResponse.setSurname(memberEntity.getSurname());
        getMemberResponse.setBirthdate(memberEntity.getBirthdate());

        return getMemberResponse;
    }

    private List<GetMemberResponse> getResponses(List<MemberEntity> memberEntities) {
        return Optional.of(memberEntities.stream().map(this::getResponse).collect(Collectors.toList())).orElse(null);
    }

    private int generatePassword() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }

    private String passwordEncoder(String password) {
        return encoder.encode(password);
    }
}