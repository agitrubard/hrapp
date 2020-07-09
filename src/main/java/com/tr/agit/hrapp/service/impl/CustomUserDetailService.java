package com.tr.agit.hrapp.service.impl;

import com.tr.agit.hrapp.model.CustomUserDetails;
import com.tr.agit.hrapp.model.entity.MemberEntity;
import com.tr.agit.hrapp.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<MemberEntity> member = memberRepository.findByName(userName);
        return member.map(CustomUserDetails::new).get();
    }
}
