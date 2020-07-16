package com.tr.agit.hrapp.repository;

import com.tr.agit.hrapp.controller.response.GetMemberResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GetMemberRepository extends JpaRepository<GetMemberResponse, Long> {
    GetMemberResponse findById(long id);

    List<GetMemberResponse> findAll();
}
