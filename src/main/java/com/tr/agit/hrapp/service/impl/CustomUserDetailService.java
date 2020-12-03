package com.tr.agit.hrapp.service.impl;

import com.tr.agit.hrapp.model.entity.MemberEntity;
import com.tr.agit.hrapp.model.entity.RoleEntity;
import com.tr.agit.hrapp.repository.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomUserDetailService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MemberEntity> member = memberRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(member.get().getUsername(), member.get().getPassword(),
                getAuthorities(member));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Optional<MemberEntity> member) {
        RoleEntity memberRole = member.get().getRole();
        if (memberRole != null) {
            return AuthorityUtils.createAuthorityList("ROLE_" + memberRole.getType().name());
        }
        return Collections.emptyList();
    }
}