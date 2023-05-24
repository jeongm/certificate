package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.domain.viewdto.HouseholdCompositionDto;
import com.nhnacademy.certificate.domain.viewdto.HouseholdNumberDto;
import com.nhnacademy.certificate.entity.HouseholdCompositionResident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HouseholdCompositionResidentRepository extends JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.HouseholdCompositionResidentPk> {
    @Query(value = "select hcr " +
            "from HouseholdCompositionResident hcr " +
            "inner join fetch hcr.household h " +
            "inner join fetch  hcr.resident r")
    HouseholdCompositionDto findByResident_ResidentSerialNumber(Integer residentSerialNumber);
//    HouseholdNumberDto getByResident_ResidentSerialNumber(Integer residentSerialNumber);

}
