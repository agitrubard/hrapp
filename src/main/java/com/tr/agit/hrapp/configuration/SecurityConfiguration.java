package com.tr.agit.hrapp.configuration;

import com.tr.agit.hrapp.repository.MemberRepository;
import com.tr.agit.hrapp.service.impl.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = MemberRepository.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    CustomUserDetailService userDetailService;

    public SecurityConfiguration(CustomUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((userDetailService));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/member/log-in", "/member/password",
                        "/member/{id}/permission", "/member/{id}/permissions",
                        "/member/{id}/resign").permitAll()
                .antMatchers("/member/sign-up", "/member/", "/member/{id}",
                        "/member/{id}/role", "/member/roles",
                        "/member/{memberId}/permission-status/{permissionId}",
                        "/member/{memberId}/resign-status", "/member/resigned").hasAnyRole("MANAGER", "HR")
                .anyRequest().authenticated()
                .and().httpBasic();
    }
}