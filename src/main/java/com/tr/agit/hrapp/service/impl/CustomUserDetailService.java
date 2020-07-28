package com.tr.agit.hrapp.service.impl;

import com.tr.agit.hrapp.model.CustomUserDetails;
import com.tr.agit.hrapp.model.entity.MemberEntity;
import com.tr.agit.hrapp.model.entity.RoleEntity;
import com.tr.agit.hrapp.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MemberEntity> member = memberRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(member.get().getUsername(), member.get().getPassword(),
                getAuthorities(member));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Optional<MemberEntity> member) {
        RoleEntity memberRole = member.map(CustomUserDetails::new).get().getRole();
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(String.valueOf(memberRole));
        return authorities;
    }

}
