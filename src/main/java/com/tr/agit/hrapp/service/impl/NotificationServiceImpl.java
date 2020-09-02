package com.tr.agit.hrapp.service.impl;

import com.tr.agit.hrapp.model.entity.MemberEntity;
import com.tr.agit.hrapp.repository.MemberRepository;
import com.tr.agit.hrapp.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private JavaMailSender javaMailSender;

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

    private void sendMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("admin@hrapp.com");
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
}