package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.domain.viewdto.HouseholdDto;
import com.nhnacademy.certificate.entity.HouseholdCompositionResident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdCompositionResidentRepository extends JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.HouseholdCompositionResidentPk> {
    HouseholdDto findByResident_ResidentSerialNumber(Integer residentSerialNumber);
}
