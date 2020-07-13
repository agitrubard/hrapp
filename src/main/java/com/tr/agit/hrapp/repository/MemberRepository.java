package com.tr.agit.hrapp.repository;

import com.tr.agit.hrapp.model.entity.MemberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByEmail(String email);

    Iterable<MemberEntity> findAll();

    Optional<MemberEntity> findByIdAndPassword(long id, String password);
}
