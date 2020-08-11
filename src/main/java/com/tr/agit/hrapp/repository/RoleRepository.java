package com.tr.agit.hrapp.repository;

import com.tr.agit.hrapp.model.entity.MemberEntity;
import com.tr.agit.hrapp.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByMemberId(Optional<MemberEntity> memberId);

    boolean existsByMemberId(Optional<MemberEntity> memberId);
}