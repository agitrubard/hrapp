package com.tr.agit.hrapp.repository;

import com.tr.agit.hrapp.model.entity.DemandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandRepository extends JpaRepository<DemandEntity, Long> {

    List<DemandEntity> findById(long id);

    List<DemandEntity> findAll();
}
