package com.tr.agit.hrapp.service.impl;

import com.tr.agit.hrapp.controller.request.UpdateResignStatusRequest;
import com.tr.agit.hrapp.controller.response.GetResignedResponse;
import com.tr.agit.hrapp.model.converter.UpdateResignStatusRequestConverter;
import com.tr.agit.hrapp.model.dto.ResignDto;
import com.tr.agit.hrapp.model.entity.MemberEntity;
import com.tr.agit.hrapp.model.entity.ResignEntity;
import com.tr.agit.hrapp.model.entity.RoleEntity;
import com.tr.agit.hrapp.model.enums.MemberStatus;
import com.tr.agit.hrapp.model.enums.ResignStatus;
import com.tr.agit.hrapp.model.enums.RoleType;
import com.tr.agit.hrapp.model.exception.MemberNotFoundException;
import com.tr.agit.hrapp.model.exception.ResignAlreadyExistException;
import com.tr.agit.hrapp.model.exception.ResignNotFoundException;
import com.tr.agit.hrapp.repository.MemberRepository;
import com.tr.agit.hrapp.repository.ResignRepository;
import com.tr.agit.hrapp.service.MemberService;
import com.tr.agit.hrapp.service.ResignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResignServiceImpl implements ResignService {

    @Autowired
    ResignRepository resignRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    private JavaMailSender javaMailSender;

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
        Optional<ResignEntity> resignEntityOptional = resignRepository.findByMemberId(id);

        boolean resignIsPresent = resignEntityOptional.isPresent();
        boolean resignStatusControl = (resignEntityOptional.get().getStatus() == ResignStatus.WAITINGFORAPPRROVAL);

        if (resignIsPresent && resignStatusControl) {
            update(updateResignStatusRequest, resignEntityOptional);
        } else {
            throw new ResignNotFoundException();
        }
    }

    @Override
    public List<GetResignedResponse> getResignedMembers() {
        List<ResignEntity> resignEntities = resignRepository.findAll();
        return getResponses(resignEntities);
    }

    @Override
    public void sendResignInformationMessage(ResignEntity resignEntity) {
        List<MemberEntity> memberEntities = memberRepository.findAll();

        for (MemberEntity member : memberEntities) {
            boolean roleControl = memberRoleControl(member.getRole());
            if (roleControl) {
                sendMail(resignEntity.getMember().getEmail(), member.getEmail(),
                        resignEntity.getMember().getName() + "'s Resign Request",
                        "Name : " + resignEntity.getMember().getName() +
                                "\nSurname : " + resignEntity.getMember().getSurname() +
                                "\nStart Working Date : " + resignEntity.getMember().getStartWorkingDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                                "\nResign Date : " + resignEntity.getResignDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                                "\nTotal Working Days : " + resignEntity.getTotalWorkingDays());
            }
        }
    }

    @Override
    public void sendResignRequestAcceptedMessage(ResignEntity resignEntity) {
        sendMail("admin@hrapp.com", resignEntity.getMember().getEmail(),
                "Resignation Request Accepted!",
                "Start Working Date : " + resignEntity.getMember().getStartWorkingDate() +
                        "\nEnd Working Date : " + resignEntity.getResignDate() +
                        "\nTotal Working Days : " + resignEntity.getTotalWorkingDays() +
                        "\nResign Status : " + resignEntity.getStatus());
    }

    @Override
    public void sendResignRequestRejectedMessage(ResignEntity resignEntity) {
        sendMail("admin@hrapp.com", resignEntity.getMember().getEmail(),
                "Resignation Request Rejected!",
                "Start Working Date : " + resignEntity.getMember().getStartWorkingDate() +
                        "\nResign Date : " + resignEntity.getResignDate() +
                        "\nTotal Working Days : " + resignEntity.getTotalWorkingDays() +
                        "\nResign Status : " + resignEntity.getStatus());
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
        long totalWorkingDays = ChronoUnit.DAYS.between(memberEntityOptional.get().getStartWorkingDate(), resignEntity.getResignDate());
        resignEntity.setTotalWorkingDays(totalWorkingDays);
        resignEntity.setStatus(ResignStatus.WAITINGFORAPPRROVAL);

        resignRepository.save(resignEntity);

        sendResignInformationMessage(resignEntity);
    }

    private void update(UpdateResignStatusRequest updateResignStatusRequest, Optional<ResignEntity> resignEntityOptional) throws MemberNotFoundException {
        ResignDto resign = UpdateResignStatusRequestConverter.convert(updateResignStatusRequest);

        resignEntityOptional.get().setStatus(resign.getStatus());

        resignRepository.save(resignEntityOptional.get());

        switch (resign.getStatus()) {
            case ACCEPTED:
                sendResignRequestAcceptedMessage(resignEntityOptional.get());
                memberService.delete(resignEntityOptional.get().getMember().getId());
                break;
            case REJECTED:
                sendResignRequestRejectedMessage(resignEntityOptional.get());
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