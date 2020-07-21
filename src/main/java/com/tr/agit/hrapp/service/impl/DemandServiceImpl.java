package com.tr.agit.hrapp.service.impl;

import com.tr.agit.hrapp.controller.request.CreateDemandRequest;
import com.tr.agit.hrapp.controller.response.GetDemandResponse;
import com.tr.agit.hrapp.model.converter.CreateDemandRequestConverter;
import com.tr.agit.hrapp.model.dto.DemandDto;
import com.tr.agit.hrapp.model.entity.DemandEntity;
import com.tr.agit.hrapp.model.entity.MemberEntity;
import com.tr.agit.hrapp.model.enums.MemberStatus;
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
    public void create(long id, CreateDemandRequest createDemandRequest) throws NullPointerException{
        MemberEntity memberEntity = memberRepository.findById(id);
        Optional<MemberEntity> memberEntityOptional = memberRepository.findByUsername(memberEntity.getUsername());
        if (memberEntityOptional.isPresent() && memberEntity.getMemberStatus() == MemberStatus.ACTIVE) {
        DemandDto demand = CreateDemandRequestConverter.convert(createDemandRequest);
            DemandEntity demandEntity = new DemandEntity();

            demandEntity.setMemberId(memberEntity);
            demandEntity.setType(demand.getType());
            demandEntity.setStartDate(demand.getStartDate());
            demandEntity.setEndDate(demand.getEndDate());
            demandEntity.setTotalDays(demand.getTotalDays());
            demandEntity.setStatus(demand.getStatus());

            demandRepository.save(demandEntity);
        } else {
           throw new NullPointerException("Member is not found or passive!");
        }
    }

    @Override
    public List<GetDemandResponse> getById(long id) {
        List<DemandEntity> demandEntities = demandRepository.findAll();
        return getResponses(demandEntities);
    }

    private GetDemandResponse getResponse(DemandEntity demandEntity) {
        GetDemandResponse getDemandResponse = new GetDemandResponse();
        getDemandResponse.setStartDate(demandEntity.getStartDate());
        getDemandResponse.setEndDate(demandEntity.getEndDate());
        getDemandResponse.setTotalDays(demandEntity.getTotalDays());
        getDemandResponse.setType(demandEntity.getType());
        getDemandResponse.setStatus(demandEntity.getStatus());

        return getDemandResponse;
    }

    private List<GetDemandResponse> getResponses(List<DemandEntity> demandEntities) {
        return demandEntities.stream()
                .map(this::getResponse)
                .collect(Collectors.toList());
    }
}
