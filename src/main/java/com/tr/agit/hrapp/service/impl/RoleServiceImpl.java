package com.tr.agit.hrapp.service.impl;

import com.tr.agit.hrapp.controller.request.AddRoleRequest;
import com.tr.agit.hrapp.model.converter.AddRoleRequestConverter;
import com.tr.agit.hrapp.model.dto.RoleDto;
import com.tr.agit.hrapp.model.entity.MemberEntity;
import com.tr.agit.hrapp.model.entity.RoleEntity;
import com.tr.agit.hrapp.model.enums.MemberStatus;
import com.tr.agit.hrapp.repository.MemberRepository;
import com.tr.agit.hrapp.repository.RoleRepository;
import com.tr.agit.hrapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void add(long id, AddRoleRequest addRoleRequest) {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findById(id);
        if (memberEntityOptional.isPresent() && memberEntityOptional.get().getStatus() == MemberStatus.ACTIVE) {
            addRole(addRoleRequest, memberEntityOptional);
        } else {
            throw new NullPointerException("Member is not found or passive!");
        }
    }

    private void addRole(AddRoleRequest addRoleRequest, Optional<MemberEntity> memberEntityOptional) {
        RoleDto role = AddRoleRequestConverter.convert(addRoleRequest);
        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setMemberId(memberEntityOptional.get());
        roleEntity.setType(role.getType());

        roleRepository.save(roleEntity);
    }
}
