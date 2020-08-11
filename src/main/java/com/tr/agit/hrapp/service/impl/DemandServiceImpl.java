package com.tr.agit.hrapp.service.impl;

import com.tr.agit.hrapp.controller.request.CreateDemandRequest;
import com.tr.agit.hrapp.controller.request.UpdateDemandRequest;
import com.tr.agit.hrapp.controller.request.UpdateDemandStatusRequest;
import com.tr.agit.hrapp.controller.response.GetDemandResponse;
import com.tr.agit.hrapp.model.converter.CreateDemandRequestConverter;
import com.tr.agit.hrapp.model.converter.UpdateDemandRequestConverter;
import com.tr.agit.hrapp.model.converter.UpdateDemandStatusRequestConverter;
import com.tr.agit.hrapp.model.dto.DemandDto;
import com.tr.agit.hrapp.model.entity.DemandEntity;
import com.tr.agit.hrapp.model.entity.MemberEntity;
import com.tr.agit.hrapp.model.enums.MemberStatus;
import com.tr.agit.hrapp.model.exception.DemandNotFoundException;
import com.tr.agit.hrapp.model.exception.MemberNotFoundException;
import com.tr.agit.hrapp.repository.DemandRepository;
import com.tr.agit.hrapp.repository.MemberRepository;
import com.tr.agit.hrapp.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DemandServiceImpl implements DemandService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    DemandRepository demandRepository;

    @Override
    public void create(long id, CreateDemandRequest createDemandRequest) throws MemberNotFoundException {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findById(id);

        boolean memberIsPresent = memberEntityOptional.isPresent();
        boolean memberStatusIsActive = (memberEntityOptional.get().getStatus() == MemberStatus.ACTIVE);

        if (memberIsPresent && memberStatusIsActive) {
            saveDemand(createDemandRequest, memberEntityOptional);
        } else {
            throw new MemberNotFoundException();
        }
    }

    @Override
    public void update(long memberId, UpdateDemandRequest updateDemandRequest, long demandId) throws MemberNotFoundException, DemandNotFoundException {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findById(memberId);

        boolean memberIsPresent = memberEntityOptional.isPresent();
        boolean memberStatusIsActive = (memberEntityOptional.get().getStatus() == MemberStatus.ACTIVE);

        if (memberIsPresent && memberStatusIsActive) {
            updateDemand(updateDemandRequest, demandId);
        } else {
            throw new MemberNotFoundException();
        }
    }

    @Override
    public void updateStatus(long memberId, UpdateDemandStatusRequest updateDemandStatusRequest, long demandId) throws MemberNotFoundException, DemandNotFoundException {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findById(memberId);

        boolean memberIsPresent = memberEntityOptional.isPresent();
        boolean memberStatusIsActive = (memberEntityOptional.get().getStatus() == MemberStatus.ACTIVE);

        if (memberIsPresent && memberStatusIsActive) {
            updateDemandStatus(updateDemandStatusRequest, demandId);
        } else {
            throw new MemberNotFoundException();
        }
    }

    @Override
    public List<GetDemandResponse> getByMemberId(long id) throws MemberNotFoundException {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findById(id);

        boolean memberIsPresent = memberEntityOptional.isPresent();

        if (memberIsPresent) {
            List<DemandEntity> demandEntities = demandRepository.findByMemberId(memberEntityOptional);

            return getResponses(demandEntities);
        } else {
            throw new MemberNotFoundException();
        }
    }

    private void saveDemand(CreateDemandRequest createDemandRequest, Optional<MemberEntity> memberEntityOptional) {
        DemandDto demand = CreateDemandRequestConverter.convert(createDemandRequest);
        DemandEntity demandEntity = new DemandEntity();

        demandEntity.setMemberId(memberEntityOptional.get());
        demandEntity.setType(demand.getType());
        demandEntity.setStartDate(demand.getStartDate());
        demandEntity.setEndDate(demand.getEndDate());
        demandEntity.setTotalDays(demand.getTotalDays());
        demandEntity.setStatus(demand.getStatus());

        demandRepository.save(demandEntity);
    }

    private void updateDemand(UpdateDemandRequest updateDemandRequest, long demandId) throws DemandNotFoundException {
        Optional<DemandEntity> demandEntityOptional = demandRepository.findById(demandId);

        boolean demandIsPresent = demandEntityOptional.isPresent();

        if (demandIsPresent) {
            DemandDto demand = UpdateDemandRequestConverter.convert(updateDemandRequest);

            demandEntityOptional.get().setType(demand.getType());
            demandEntityOptional.get().setStartDate(demand.getStartDate());
            demandEntityOptional.get().setEndDate(demand.getEndDate());
            demandEntityOptional.get().setTotalDays(demand.getTotalDays());

            demandRepository.save(demandEntityOptional.get());
        } else {
            throw new DemandNotFoundException();
        }
    }

    private void updateDemandStatus(UpdateDemandStatusRequest updateDemandStatusRequest, long demandId) throws DemandNotFoundException {
        Optional<DemandEntity> demandEntityOptional = demandRepository.findById(demandId);

        boolean demandIsPresent = demandEntityOptional.isPresent();

        if (demandIsPresent) {
            DemandDto demand = UpdateDemandStatusRequestConverter.convert(updateDemandStatusRequest);

            demandEntityOptional.get().setStatus(demand.getStatus());

            demandRepository.save(demandEntityOptional.get());
        } else {
            throw new DemandNotFoundException();
        }
    }

    private GetDemandResponse getResponse(DemandEntity demandEntity) {
        GetDemandResponse getDemandResponse = new GetDemandResponse();

        getDemandResponse.setType(demandEntity.getType());
        getDemandResponse.setStartDate(demandEntity.getStartDate());
        getDemandResponse.setEndDate(demandEntity.getEndDate());
        getDemandResponse.setTotalDays(demandEntity.getTotalDays());
        getDemandResponse.setStatus(demandEntity.getStatus());

        return getDemandResponse;
    }

    private List<GetDemandResponse> getResponses(List<DemandEntity> demandEntities) {
        return Optional.of(demandEntities.stream().map(this::getResponse).collect(Collectors.toList())).orElse(null);
    }
}