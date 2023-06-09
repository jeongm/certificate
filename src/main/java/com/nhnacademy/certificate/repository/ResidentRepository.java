package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.domain.viewdto.ResidentDto;
import com.nhnacademy.certificate.domain.viewdto.FamilyResidentDto;
import com.nhnacademy.certificate.domain.viewdto.ResidentNumberNameDto;
import com.nhnacademy.certificate.entity.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResidentRepository extends JpaRepository<Resident,Integer> {

    ResidentDto findByResidentSerialNumber(Integer serialNumber);

    boolean existsByResidentRegistrationNumber(String residentRegistrationNumber);

    @Query(value = "select fr.family_relationship_code as familyRelationshipCode, r.name  ,r.birth_date as birthDate , r.resident_registration_number as residentRegistrationNumber , r.gender_code as genderCode " +
            "from family_relationship as fr inner join resident r on fr.family_resident_serial_number = r.resident_serial_number " +
            "where fr.base_resident_serial_number=?1", nativeQuery = true)
    List<FamilyResidentDto> findByFamilyResident(Integer baseResident);

    ResidentNumberNameDto findByMemberId(String memberId);

    Page<ResidentDto> getAllBy(Pageable pageable);

}
