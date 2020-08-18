package com.tr.agit.hrapp.repository;

import com.tr.agit.hrapp.model.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

    List<PermissionEntity> findByMemberId(long id);

    List<PermissionEntity> findAll();
}