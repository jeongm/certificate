package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.domain.entitydto.BirthDeathReportResidentDto;
import com.nhnacademy.certificate.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthDeathReportResidentRepository extends JpaRepository<BirthDeathReportResident, BirthDeathReportResident.BirthDeathReportResidentPk> {
    BirthDeathReportResidentDto findByBirthDeathReportResidentPk(BirthDeathReportResident.BirthDeathReportResidentPk birthDeathReportResidentPk);
}
