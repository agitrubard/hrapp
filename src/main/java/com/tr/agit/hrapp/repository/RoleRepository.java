package com.tr.agit.hrapp.repository;

import com.tr.agit.hrapp.model.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<MemberEntity, Long> {

}
