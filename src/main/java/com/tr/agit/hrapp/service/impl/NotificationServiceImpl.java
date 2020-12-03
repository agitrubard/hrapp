package com.tr.agit.hrapp.service.impl;

import com.tr.agit.hrapp.model.entity.MemberEntity;
import com.tr.agit.hrapp.model.entity.PermissionEntity;
import com.tr.agit.hrapp.model.entity.ResignEntity;
import com.tr.agit.hrapp.model.entity.RoleEntity;
import com.tr.agit.hrapp.model.enums.RoleType;
import com.tr.agit.hrapp.repository.MemberRepository;
import com.tr.agit.hrapp.service.NotificationService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    MemberRepository memberRepository;
    JavaMailSender javaMailSender;

    public NotificationServiceImpl(MemberRepository memberRepository, JavaMailSender javaMailSender) {
        this.memberRepository = memberRepository;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendPersonalInformationMessage(MemberEntity memberEntity, String tempPassword) {
        sendMail(memberEntity.getEmail(),
                "Welcome to HRApp",
                "Username : " + memberEntity.getUsername() + "\nTemporary Password : " + tempPassword);
    }

    @Override
    @Scheduled(cron = "0 0 9 * * *", zone = "Europe/Istanbul") //"*/5 * * * * *"
    public void sendBirthdayMessage() {
        List<MemberEntity> memberEntities = memberRepository.findAll();

        for (MemberEntity member : memberEntities) {
            boolean dateControl = birthdateControl(member.getBirthdate());
            if (dateControl) {
                sendMail(member.getEmail(),
                        "Happy Birthday!",
                        "Happy birthday to you " + member.getName() + "!");
            }
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

    @Override
    public void sendResignInformationMessage(ResignEntity resignEntity) {
        List<MemberEntity> memberEntities = memberRepository.findAll();

        for (MemberEntity member : memberEntities) {
            boolean roleControl = memberRoleControl(member.getRole());
            if (roleControl) {
                sendMail(resignEntity.getMember().getEmail(), member.getEmail(),
                        resignEntity.getMember().getName() + "'s Resign Request",
                        "Member ID : " + resignEntity.getMember().getId() +
                                "\nName : " + resignEntity.getMember().getName() +
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
                "Your Resignation Request Accepted!",
                "Start Working Date : " + resignEntity.getMember().getStartWorkingDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                        "\nEnd Working Date : " + resignEntity.getResignDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                        "\nTotal Working Days : " + resignEntity.getTotalWorkingDays() +
                        "\nResign Status : " + resignEntity.getStatus());
    }

    @Override
    public void sendResignRequestRejectedMessage(ResignEntity resignEntity) {
        sendMail("admin@hrapp.com", resignEntity.getMember().getEmail(),
                "Your Resignation Request Rejected!",
                "Start Working Date : " + resignEntity.getMember().getStartWorkingDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                        "\nResign Date : " + resignEntity.getResignDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                        "\nTotal Working Days : " + resignEntity.getTotalWorkingDays() +
                        "\nResign Status : " + resignEntity.getStatus());
    }

    private void sendMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("admin@hrapp.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }

    private void sendMail(String from, String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }

    private boolean birthdateControl(LocalDate birthdate) {
        boolean monthControl = (birthdate.getMonthValue() == LocalDate.now().getMonthValue());
        boolean dayControl = (birthdate.getDayOfMonth() == LocalDate.now().getDayOfMonth());

        if (monthControl) return dayControl;
        return false;
    }

    private boolean memberRoleControl(RoleEntity role) {
        try {
            return role.getType() == RoleType.HR;
        } catch (NullPointerException e) {
            return false;
        }
    }
}