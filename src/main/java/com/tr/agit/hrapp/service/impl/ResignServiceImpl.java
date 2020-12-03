package com.tr.agit.hrapp.service.impl;

import com.tr.agit.hrapp.controller.request.UpdateResignStatusRequest;
import com.tr.agit.hrapp.controller.response.GetResignedResponse;
import com.tr.agit.hrapp.model.converter.UpdateResignStatusRequestConverter;
import com.tr.agit.hrapp.model.dto.ResignDto;
import com.tr.agit.hrapp.model.entity.MemberEntity;
import com.tr.agit.hrapp.model.entity.PermissionEntity;
import com.tr.agit.hrapp.model.entity.ResignEntity;
import com.tr.agit.hrapp.model.enums.MemberStatus;
import com.tr.agit.hrapp.model.enums.PermissionStatus;
import com.tr.agit.hrapp.model.enums.ResignStatus;
import com.tr.agit.hrapp.model.exception.MemberNotFoundException;
import com.tr.agit.hrapp.model.exception.ResignAlreadyExistException;
import com.tr.agit.hrapp.model.exception.ResignNotFoundException;
import com.tr.agit.hrapp.repository.MemberRepository;
import com.tr.agit.hrapp.repository.PermissionRepository;
import com.tr.agit.hrapp.repository.ResignRepository;
import com.tr.agit.hrapp.service.MemberService;
import com.tr.agit.hrapp.service.NotificationService;
import com.tr.agit.hrapp.service.ResignService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResignServiceImpl implements ResignService {

    ResignRepository resignRepository;
    MemberRepository memberRepository;
    PermissionRepository permissionRepository;
    MemberService memberService;
    NotificationService notificationService;
    JavaMailSender javaMailSender;

    public ResignServiceImpl(ResignRepository resignRepository,
                             MemberRepository memberRepository,
                             PermissionRepository permissionRepository,
                             MemberService memberService,
                             NotificationService notificationService,
                             JavaMailSender javaMailSender) {
        this.resignRepository = resignRepository;
        this.memberRepository = memberRepository;
        this.permissionRepository = permissionRepository;
        this.memberService = memberService;
        this.notificationService = notificationService;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void create(long id) throws MemberNotFoundException, ResignAlreadyExistException {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findById(id);

        boolean memberIsPresent = memberEntityOptional.isPresent();
        boolean memberStatusIsActive = (memberEntityOptional.get().getStatus() == MemberStatus.ACTIVE);

        if (memberIsPresent && memberStatusIsActive) {
            create(memberEntityOptional);
        } else {
            throw new MemberNotFoundException();
        }
    }

    @Override
    public void updateStatus(long id, UpdateResignStatusRequest updateResignStatusRequest) throws ResignNotFoundException, MemberNotFoundException {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findById(id);

        boolean memberIsPresent = memberEntityOptional.isPresent();
        boolean memberStatusIsActive = (memberEntityOptional.get().getStatus() == MemberStatus.ACTIVE);

        if (memberIsPresent && memberStatusIsActive) {
            updateResignStatus(id, updateResignStatusRequest);
        } else {
            throw new MemberNotFoundException();
        }
    }

    @Override
    public List<GetResignedResponse> getResignedMembers() {
        List<ResignEntity> resignEntities = resignRepository.findAll();
        return getResponses(resignEntities);
    }

    private void create(Optional<MemberEntity> memberEntityOptional) throws ResignAlreadyExistException {
        Optional<ResignEntity> resignEntityOptional = resignRepository.findByMemberId(memberEntityOptional.get().getId());

        boolean resignIsPresent = resignEntityOptional.isPresent();

        if (!(resignIsPresent)) {
            createResign(memberEntityOptional);
        } else {
            throw new ResignAlreadyExistException();
        }
    }

    private void createResign(Optional<MemberEntity> memberEntityOptional) {
        ResignEntity resignEntity = new ResignEntity();

        resignEntity.setMember(memberEntityOptional.get());
        resignEntity.setResignDate(LocalDate.now());
        long totalWorkingDays = calculateTotalWorkingDays(memberEntityOptional.get(), resignEntity.getResignDate());
        resignEntity.setTotalWorkingDays(totalWorkingDays);
        resignEntity.setStatus(ResignStatus.WAITINGFORAPPRROVAL);

        resignRepository.save(resignEntity);

        notificationService.sendResignInformationMessage(resignEntity);
    }

    private long calculateTotalWorkingDays(MemberEntity memberEntity, LocalDate resignDate) {
        long totalWorkingDays = ChronoUnit.DAYS.between(memberEntity.getStartWorkingDate(), resignDate);

        List<PermissionEntity> permissionEntities = permissionRepository.findByMemberId(memberEntity.getId());

        for (PermissionEntity permission : permissionEntities) {
            boolean permissionControl = (permission.getStatus() == PermissionStatus.ACCEPTED);
            if (permissionControl) {
                totalWorkingDays -= permission.getTotalDays();
            }
        }
        return totalWorkingDays;
    }

    private void updateResignStatus(long id, UpdateResignStatusRequest updateResignStatusRequest) throws MemberNotFoundException, ResignNotFoundException {
        Optional<ResignEntity> resignEntityOptional = resignRepository.findByMemberId(id);

        boolean resignIsPresent = resignEntityOptional.isPresent();
        boolean resignStatusControl = (resignEntityOptional.get().getStatus() == ResignStatus.WAITINGFORAPPRROVAL);

        if (resignIsPresent && resignStatusControl) {
            ResignDto resign = UpdateResignStatusRequestConverter.convert(updateResignStatusRequest);

            resignEntityOptional.get().setStatus(resign.getStatus());

            resignRepository.save(resignEntityOptional.get());

            sendRequestMessage(resignEntityOptional, resign);
        } else {
            throw new ResignNotFoundException();
        }
    }

    private void sendRequestMessage(Optional<ResignEntity> resignEntityOptional, ResignDto resign) throws MemberNotFoundException {
        switch (resign.getStatus()) {
            case ACCEPTED:
                notificationService.sendResignRequestAcceptedMessage(resignEntityOptional.get());
                memberService.delete(resignEntityOptional.get().getMember().getId());
                break;
            case REJECTED:
                notificationService.sendResignRequestRejectedMessage(resignEntityOptional.get());
                break;
        }
    }

    private GetResignedResponse getResponse(ResignEntity resignEntity) {
        GetResignedResponse getResignedResponse = new GetResignedResponse();

        getResignedResponse.setMemberId(resignEntity.getMember().getId());
        getResignedResponse.setName(resignEntity.getMember().getName());
        getResignedResponse.setSurname(resignEntity.getMember().getSurname());
        getResignedResponse.setStartWorkingDate(resignEntity.getMember().getStartWorkingDate());
        getResignedResponse.setEndWorkingDate(resignEntity.getResignDate());
        getResignedResponse.setTotalWorkingDays(resignEntity.getTotalWorkingDays());
        getResignedResponse.setStatus(resignEntity.getStatus());

        return getResignedResponse;
    }

    private List<GetResignedResponse> getResponses(List<ResignEntity> resignEntities) {
        return Optional.of(resignEntities.stream().map(this::getResponse).collect(Collectors.toList())).orElse(null);
    }
}