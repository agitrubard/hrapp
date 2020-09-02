package com.tr.agit.hrapp.service.impl;

import com.tr.agit.hrapp.controller.request.CreatePermissionRequest;
import com.tr.agit.hrapp.controller.request.UpdatePermissionRequest;
import com.tr.agit.hrapp.controller.request.UpdatePermissionStatusRequest;
import com.tr.agit.hrapp.controller.response.GetPermissionResponse;
import com.tr.agit.hrapp.model.converter.CreatePermissionRequestConverter;
import com.tr.agit.hrapp.model.converter.UpdatePermissionRequestConverter;
import com.tr.agit.hrapp.model.converter.UpdatePermissionStatusRequestConverter;
import com.tr.agit.hrapp.model.dto.PermissionDto;
import com.tr.agit.hrapp.model.entity.MemberEntity;
import com.tr.agit.hrapp.model.entity.PermissionEntity;
import com.tr.agit.hrapp.model.enums.MemberStatus;
import com.tr.agit.hrapp.model.enums.PermissionStatus;
import com.tr.agit.hrapp.model.exception.MemberNotFoundException;
import com.tr.agit.hrapp.model.exception.PermissionNotFoundException;
import com.tr.agit.hrapp.repository.MemberRepository;
import com.tr.agit.hrapp.repository.PermissionRepository;
import com.tr.agit.hrapp.service.NotificationService;
import com.tr.agit.hrapp.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    NotificationService notificationService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void create(long id, CreatePermissionRequest createPermissionRequest) throws MemberNotFoundException {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findById(id);

        boolean memberIsPresent = memberEntityOptional.isPresent();
        boolean memberStatusIsActive = (memberEntityOptional.get().getStatus() == MemberStatus.ACTIVE);

        if (memberIsPresent && memberStatusIsActive) {
            createPermission(createPermissionRequest, memberEntityOptional);
        } else {
            throw new MemberNotFoundException();
        }
    }

    @Override
    public void update(long memberId, UpdatePermissionRequest updatePermissionRequest, long permissionId) throws MemberNotFoundException, PermissionNotFoundException {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findById(memberId);

        boolean memberIsPresent = memberEntityOptional.isPresent();
        boolean memberStatusIsActive = (memberEntityOptional.get().getStatus() == MemberStatus.ACTIVE);

        if (memberIsPresent && memberStatusIsActive) {
            updatePermission(updatePermissionRequest, permissionId);
        } else {
            throw new MemberNotFoundException();
        }
    }

    @Override
    public void updateStatus(long memberId, UpdatePermissionStatusRequest updatePermissionStatusRequest, long permissionId) throws MemberNotFoundException, PermissionNotFoundException {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findById(memberId);

        boolean memberIsPresent = memberEntityOptional.isPresent();
        boolean memberStatusIsActive = (memberEntityOptional.get().getStatus() == MemberStatus.ACTIVE);

        if (memberIsPresent && memberStatusIsActive) {
            updatePermissionStatus(updatePermissionStatusRequest, permissionId);
        } else {
            throw new MemberNotFoundException();
        }
    }

    @Override
    public List<GetPermissionResponse> getPermissionsByMemberId(long id) throws MemberNotFoundException {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findById(id);

        boolean memberIsPresent = memberEntityOptional.isPresent();

        if (memberIsPresent) {
            List<PermissionEntity> permissionEntities = permissionRepository.findByMemberId(memberEntityOptional.get().getId());

            return getResponses(permissionEntities);
        } else {
            throw new MemberNotFoundException();
        }
    }

    private void createPermission(CreatePermissionRequest createPermissionRequest, Optional<MemberEntity> memberEntityOptional) {
        PermissionDto permission = CreatePermissionRequestConverter.convert(createPermissionRequest);
        PermissionEntity permissionEntity = new PermissionEntity();

        permissionEntity.setMember(memberEntityOptional.get());
        permissionEntity.setType(permission.getType());
        permissionEntity.setStartDate(permission.getStartDate());
        permissionEntity.setEndDate(permission.getEndDate());
        permissionEntity.setTotalDays(permission.getTotalDays());
        permissionEntity.setStatus(permission.getStatus());

        permissionRepository.save(permissionEntity);

        notificationService.sendPermissionInformationMessage(permissionEntity);
    }

    private void updatePermission(UpdatePermissionRequest updatePermissionRequest, long permissionId) throws PermissionNotFoundException {
        Optional<PermissionEntity> permissionEntityOptional = permissionRepository.findById(permissionId);

        boolean permissionIsPresent = permissionEntityOptional.isPresent();

        if (permissionIsPresent) {
            PermissionDto permission = UpdatePermissionRequestConverter.convert(updatePermissionRequest);

            permissionEntityOptional.get().setType(permission.getType());
            permissionEntityOptional.get().setStartDate(permission.getStartDate());
            permissionEntityOptional.get().setEndDate(permission.getEndDate());
            permissionEntityOptional.get().setTotalDays(permission.getTotalDays());

            permissionRepository.save(permissionEntityOptional.get());

            sendRequestMessage(permissionEntityOptional, permission);
        } else {
            throw new PermissionNotFoundException();
        }
    }

    private void updatePermissionStatus(UpdatePermissionStatusRequest updatePermissionStatusRequest, long permissionId) throws PermissionNotFoundException {
        Optional<PermissionEntity> permissionEntityOptional = permissionRepository.findById(permissionId);

        boolean permissionIsPresent = permissionEntityOptional.isPresent();
        boolean permissionStatusControl = (permissionEntityOptional.get().getStatus() == PermissionStatus.WAITINGFORAPPRROVAL);

        if (permissionIsPresent && permissionStatusControl) {
            PermissionDto permission = UpdatePermissionStatusRequestConverter.convert(updatePermissionStatusRequest);

            permissionEntityOptional.get().setStatus(permission.getStatus());

            permissionRepository.save(permissionEntityOptional.get());

            sendRequestMessage(permissionEntityOptional, permission);
        } else {
            throw new PermissionNotFoundException();
        }
    }

    private void sendRequestMessage(Optional<PermissionEntity> permissionEntityOptional, PermissionDto permission) {
        switch (permission.getStatus()) {
            case ACCEPTED:
                notificationService.sendPermissionRequestAcceptedMessage(permissionEntityOptional.get());
                break;
            case REJECTED:
                notificationService.sendPermissionRequestRejectedMessage(permissionEntityOptional.get());
                break;
        }
    }

    private GetPermissionResponse getResponse(PermissionEntity permissionEntity) {
        GetPermissionResponse getPermissionResponse = new GetPermissionResponse();

        getPermissionResponse.setType(permissionEntity.getType());
        getPermissionResponse.setStartDate(permissionEntity.getStartDate());
        getPermissionResponse.setEndDate(permissionEntity.getEndDate());
        getPermissionResponse.setTotalDays(permissionEntity.getTotalDays());
        getPermissionResponse.setStatus(permissionEntity.getStatus());

        return getPermissionResponse;
    }

    private List<GetPermissionResponse> getResponses(List<PermissionEntity> permissionEntities) {
        return Optional.of(permissionEntities.stream().map(this::getResponse).collect(Collectors.toList())).orElse(null);
    }
}