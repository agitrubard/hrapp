package com.tr.agit.hrapp.repository;

import com.tr.agit.hrapp.model.entity.DemandEntity;
import com.tr.agit.hrapp.model.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DemandRepository extends JpaRepository<DemandEntity, Long> {

    List<DemandEntity> findByMemberId(Optional<MemberEntity> memberId);

    List<DemandEntity> findAll();
}