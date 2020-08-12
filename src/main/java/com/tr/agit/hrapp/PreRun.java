package com.tr.agit.hrapp;

import com.tr.agit.hrapp.model.entity.MemberEntity;
import com.tr.agit.hrapp.model.entity.RoleEntity;
import com.tr.agit.hrapp.model.enums.MemberStatus;
import com.tr.agit.hrapp.model.enums.RoleType;
import com.tr.agit.hrapp.repository.MemberRepository;
import com.tr.agit.hrapp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Service
public class PreRun {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    RoleRepository roleRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostConstruct
    private void addAdmin() {
        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setEmail("admin@hrapp.com");
        memberEntity.setUsername("admin");
        memberEntity.setPassword(passwordEncoder());
        memberEntity.setName("Admin");
        memberEntity.setSurname("HRApp");
        memberEntity.setBirthdate(LocalDate.now());
        memberEntity.setStatus(MemberStatus.ACTIVE);

        memberRepository.save(memberEntity);

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setMemberId(memberEntity);
        roleEntity.setType(RoleType.MANAGER);

        roleRepository.save(roleEntity);
    }

    private String passwordEncoder() {
        return encoder.encode("admin");
    }
}