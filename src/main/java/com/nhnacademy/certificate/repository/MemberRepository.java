package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,String> {
}
