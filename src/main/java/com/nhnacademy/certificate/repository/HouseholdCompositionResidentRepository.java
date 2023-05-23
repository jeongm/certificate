package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.entity.HouseholdCompositionResident;
import com.nhnacademy.certificate.entity.HouseholdNumberDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdCompositionResidentRepository extends JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.HouseholdCompositionResidentPk> {
    HouseholdNumberDto findByHouseholdCompositionResidentPk_ResidentSerialNumber(Integer residentSerialNumber);
}
