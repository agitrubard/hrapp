package com.tr.agit.hrapp.service.impl;

import com.tr.agit.hrapp.controller.request.AddRoleRequest;
import com.tr.agit.hrapp.controller.response.GetRoleResponse;
import com.tr.agit.hrapp.model.converter.AddRoleRequestConverter;
import com.tr.agit.hrapp.model.dto.RoleDto;
import com.tr.agit.hrapp.model.entity.MemberEntity;
import com.tr.agit.hrapp.model.entity.RoleEntity;
import com.tr.agit.hrapp.model.enums.MemberStatus;
import com.tr.agit.hrapp.model.exception.MemberNotFoundException;
import com.tr.agit.hrapp.model.exception.RoleNotFoundException;
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
    RoleRepository roleRepository;

    @Autowired
    MemberRepository memberRepository;

    @Override
    public void add(long id, AddRoleRequest addRoleRequest) throws MemberNotFoundException {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findById(id);

        boolean memberIsPresent = memberEntityOptional.isPresent();
        boolean memberStatusIsActive = (memberEntityOptional.get().getStatus() == MemberStatus.ACTIVE);

        if (memberIsPresent && memberStatusIsActive) {
            Optional<RoleEntity> roleEntityOptional = roleRepository.findByMemberId(memberEntityOptional.get().getId());

            if (!(roleEntityOptional.isPresent())) {
                addRole(addRoleRequest, memberEntityOptional);
            } else {
                updateRole(addRoleRequest, roleEntityOptional);
            }
        } else {
            throw new MemberNotFoundException();
        }
    }

    @Override
    public GetRoleResponse getRoleByMemberId(long id) throws MemberNotFoundException, RoleNotFoundException {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findById(id);

        boolean memberIsPresent = memberEntityOptional.isPresent();

        if (memberIsPresent) {
            return getRole(memberEntityOptional);
        } else {
            throw new MemberNotFoundException();
        }
    }

    @Override
    public List<GetRoleResponse> getMembersRole() {
        List<RoleEntity> roleEntities = roleRepository.findAll();
        return getResponses(roleEntities);
    }

    private void addRole(AddRoleRequest addRoleRequest, Optional<MemberEntity> memberEntityOptional) {
        RoleDto role = AddRoleRequestConverter.convert(addRoleRequest);
        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setMember(memberEntityOptional.get());
        roleEntity.setType(role.getType());

        roleRepository.save(roleEntity);
    }

    private void updateRole(AddRoleRequest addRoleRequest, Optional<RoleEntity> roleEntityOptional) {
        RoleDto role = AddRoleRequestConverter.convert(addRoleRequest);

        roleEntityOptional.get().setType(role.getType());

        roleRepository.save(roleEntityOptional.get());
    }

    private GetRoleResponse getRole(Optional<MemberEntity> memberEntityOptional) throws RoleNotFoundException {
        Optional<RoleEntity> roleEntity = roleRepository.findByMemberId(memberEntityOptional.get().getId());

        boolean roleIsPresent = roleEntity.isPresent();

        if (roleIsPresent) {
            return getResponse(roleEntity.get());
        } else {
            throw new RoleNotFoundException();
        }
    }

    private GetRoleResponse getResponse(RoleEntity roleEntity) {
        GetRoleResponse getRoleResponse = new GetRoleResponse();

        getRoleResponse.setMemberId(roleEntity.getMember().getId());
        getRoleResponse.setName(roleEntity.getMember().getName());
        getRoleResponse.setSurname(roleEntity.getMember().getSurname());
        getRoleResponse.setType(roleEntity.getType());

        return getRoleResponse;
    }

    private List<GetRoleResponse> getResponses(List<RoleEntity> roleEntities) {
        return Optional.of(roleEntities.stream().map(this::getResponse).collect(Collectors.toList())).orElse(null);
    }
}