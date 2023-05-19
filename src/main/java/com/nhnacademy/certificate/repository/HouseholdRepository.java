package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdRepository extends JpaRepository<Household,Integer> {
}
