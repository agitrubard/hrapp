package com.tr.agit.hrapp.service;

import com.tr.agit.hrapp.model.entity.MemberEntity;
import com.tr.agit.hrapp.model.entity.PermissionEntity;
import com.tr.agit.hrapp.model.entity.ResignEntity;

public interface NotificationService {

    void sendPersonalInformationMessage(MemberEntity memberEntity, String tempPassword);

    void sendBirthdayMessage();

    void sendPermissionInformationMessage(PermissionEntity permissionEntity);

    void sendPermissionRequestAcceptedMessage(PermissionEntity permissionEntity);

    void sendPermissionRequestRejectedMessage(PermissionEntity permissionEntity);

    void sendResignInformationMessage(ResignEntity resignEntity);

    void sendResignRequestAcceptedMessage(ResignEntity resignEntity);

    void sendResignRequestRejectedMessage(ResignEntity resignEntity);
}