package com.tr.agit.hrapp.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails extends MemberEntity implements UserDetails {

    public CustomUserDetails(final MemberEntity member) {
        new MemberEntity(member.getId(), member.getName(), member.getSurname(), member.getPassword(), member.getEmail(), member.getTempPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuthorities()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getAuthority()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return super.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
