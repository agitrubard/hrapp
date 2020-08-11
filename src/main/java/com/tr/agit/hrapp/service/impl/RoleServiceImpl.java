package com.tr.agit.hrapp.service.impl;

import com.tr.agit.hrapp.controller.request.AddRoleRequest;
import com.tr.agit.hrapp.controller.response.GetRoleResponse;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            Optional<RoleEntity> roleEntityOptional = roleRepository.findByMemberId(memberEntityOptional);

            if (roleEntityOptional.isPresent()) {
                updateRole(addRoleRequest, roleEntityOptional);
            } else {
                addRole(addRoleRequest, memberEntityOptional);
            }
        } else {
            throw new NullPointerException("Member is not found or passive!");
        }
    }

    @Override
    public GetRoleResponse getByMemberId(long id) {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findById(id);

        if (roleRepository.existsByMemberId(memberEntityOptional)) {
            Optional<RoleEntity> roleEntity = roleRepository.findByMemberId(memberEntityOptional);

            return getResponse(roleEntity.get());
        } else {
            return null;
        }
    }

    @Override
    public List<GetRoleResponse> get() {
        List<RoleEntity> roleEntities = roleRepository.findAll();
        return getResponses(roleEntities);
    }

    private void addRole(AddRoleRequest addRoleRequest, Optional<MemberEntity> memberEntityOptional) {
        RoleDto role = AddRoleRequestConverter.convert(addRoleRequest);
        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setMemberId(memberEntityOptional.get());
        roleEntity.setType(role.getType());

        roleRepository.save(roleEntity);
    }

    private void updateRole(AddRoleRequest addRoleRequest, Optional<RoleEntity> roleEntityOptional) {
        RoleDto role = AddRoleRequestConverter.convert(addRoleRequest);

        roleEntityOptional.get().setType(role.getType());

        roleRepository.save(roleEntityOptional.get());
    }

    private GetRoleResponse getResponse(RoleEntity roleEntity) {
        GetRoleResponse getRoleResponse = new GetRoleResponse();

        getRoleResponse.setUsername(roleEntity.getMemberId().getUsername());
        getRoleResponse.setType(roleEntity.getType());

        return getRoleResponse;
    }

    private List<GetRoleResponse> getResponses(List<RoleEntity> roleEntities) {
        return roleEntities.stream()
                .map(this::getResponse)
                .collect(Collectors.toList());
    }
}