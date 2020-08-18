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
import com.tr.agit.hrapp.model.entity.RoleEntity;
import com.tr.agit.hrapp.model.enums.MemberStatus;
import com.tr.agit.hrapp.model.enums.PermissionStatus;
import com.tr.agit.hrapp.model.enums.RoleType;
import com.tr.agit.hrapp.model.exception.MemberNotFoundException;
import com.tr.agit.hrapp.model.exception.PermissionNotFoundException;
import com.tr.agit.hrapp.repository.MemberRepository;
import com.tr.agit.hrapp.repository.PermissionRepository;
import com.tr.agit.hrapp.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
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

    @Override
    public void sendPermissionInformationMessage(PermissionEntity permissionEntity) {
        List<MemberEntity> memberEntities = memberRepository.findAll();

        for (MemberEntity member : memberEntities) {
            boolean roleControl = memberRoleControl(member.getRole());
            if (roleControl) {
                sendMail(permissionEntity.getMember().getEmail(), member.getEmail(),
                        permissionEntity.getMember().getName() + "'s Permission Request",
                        "Member ID : " + permissionEntity.getMember().getId() +
                                "\nName : " + permissionEntity.getMember().getName() +
                                "\nSurname : " + permissionEntity.getMember().getSurname() +
                                "\nPermission Type : " + permissionEntity.getType() +
                                "\nStart Permission Date : " + permissionEntity.getMember().getStartWorkingDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                                "\nEnd Permission Date : " + permissionEntity.getEndDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                                "\nTotal Permission Days : " + permissionEntity.getTotalDays());
            }
        }
    }

    @Override
    public void sendPermissionRequestAcceptedMessage(PermissionEntity permissionEntity) {
        sendMail("admin@hrapp.com", permissionEntity.getMember().getEmail(),
                "Your Permission Request Accepted!",
                "Permission Type : " + permissionEntity.getType() +
                        "\nStart Permission Date : " + permissionEntity.getMember().getStartWorkingDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                        "\nEnd Permission Date : " + permissionEntity.getEndDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                        "\nTotal Permission Days : " + permissionEntity.getTotalDays() +
                        "\nPermission Status : " + permissionEntity.getStatus());
    }

    @Override
    public void sendPermissionRequestRejectedMessage(PermissionEntity permissionEntity) {
        sendMail("admin@hrapp.com", permissionEntity.getMember().getEmail(),
                "Your Permission Request Rejected!",
                "Permission Type : " + permissionEntity.getType() +
                        "\nStart Permission Date : " + permissionEntity.getMember().getStartWorkingDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                        "\nEnd Permission Date : " + permissionEntity.getEndDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                        "\nTotal Permission Days : " + permissionEntity.getTotalDays() +
                        "\nPermission Status : " + permissionEntity.getStatus());
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

        sendPermissionInformationMessage(permissionEntity);
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
                sendPermissionRequestAcceptedMessage(permissionEntityOptional.get());
                break;
            case REJECTED:
                sendPermissionRequestRejectedMessage(permissionEntityOptional.get());
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

    private void sendMail(String from, String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }

    private boolean memberRoleControl(RoleEntity role) {
        try {
            return role.getType() == RoleType.HR;
        } catch (NullPointerException e) {
            return false;
        }
    }
}