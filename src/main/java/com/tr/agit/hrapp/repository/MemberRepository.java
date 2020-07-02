package com.tr.agit.hrapp.repository;

import com.tr.agit.hrapp.model.MemberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<MemberEntity, Long> {
}
