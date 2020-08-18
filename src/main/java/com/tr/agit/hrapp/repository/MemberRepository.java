package com.tr.agit.hrapp.repository;

import com.tr.agit.hrapp.model.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findById(long id);

    List<MemberEntity> findAll();

    Optional<MemberEntity> findByEmail(String email);

    Optional<MemberEntity> findByUsername(String username);
}