package com.tr.agit.hrapp.service;

import com.tr.agit.hrapp.controller.request.UpdateResignStatusRequest;
import com.tr.agit.hrapp.controller.response.GetResignedResponse;
import com.tr.agit.hrapp.model.exception.MemberNotFoundException;
import com.tr.agit.hrapp.model.exception.ResignAlreadyExistException;
import com.tr.agit.hrapp.model.exception.ResignNotFoundException;

import java.util.List;

public interface ResignService {

    void create(long id) throws MemberNotFoundException, ResignAlreadyExistException;

    void updateStatus(long id, UpdateResignStatusRequest updateResignStatusRequest) throws ResignNotFoundException, MemberNotFoundException;

    List<GetResignedResponse> getResignedMembers();
}