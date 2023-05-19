package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.domain.entitydto.ResidentDto;
import com.nhnacademy.certificate.domain.entitydto.ResidentSerialNumberAndNameDto;
import com.nhnacademy.certificate.entity.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident,Integer> {

    ResidentDto findByResidentSerialNumber(Integer serialNumber);
    Page<ResidentSerialNumberAndNameDto> getAllBy(Pageable pageable);
}
