package com.tr.agit.hrapp.repository;

import com.tr.agit.hrapp.model.entity.ResignEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResignRepository extends JpaRepository<ResignEntity, Long> {

    Optional<ResignEntity> findByMemberId(long id);

    List<ResignEntity> findAll();
}