package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.domain.viewdto.HouseholdCompositionDto;
import com.nhnacademy.certificate.entity.HouseholdCompositionResident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HouseholdCompositionResidentRepository extends JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.HouseholdCompositionResidentPk> {
    @Query(value = "select hcr " +
            "from HouseholdCompositionResident hcr " +
            "inner join fetch hcr.household h " +
            "inner join fetch h.householdCompositionResidents hcr2 " +
            "inner join fetch hcr2.resident r2 " +
            "inner join fetch h.householdMovementAddresses hma " +
            "inner join fetch  hcr.resident r " +
            "where r.residentSerialNumber = ?1")
    HouseholdCompositionDto findByResident_ResidentSerialNumber(Integer residentSerialNumber);

}
