package com.tr.agit.hrapp.service;

import com.tr.agit.hrapp.model.entity.MemberEntity;

public interface NotificationService {

    void sendPersonalInformationMessage(MemberEntity memberEntity, String tempPassword);

    void sendBirthdayMessage();
}