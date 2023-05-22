package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.domain.entitydto.BirthDeathReportResidentDto;
import com.nhnacademy.certificate.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BirthDeathReportResidentRepository extends JpaRepository<BirthDeathReportResident, BirthDeathReportResident.BirthDeathReportResidentPk> {
    BirthDeathReportResidentDto findByBirthDeathReportResidentPk(BirthDeathReportResident.BirthDeathReportResidentPk birthDeathReportResidentPk);

    boolean existsByTargetResident_ResidentSerialNumberAndBirthDeathReportResidentPk_BirthDeathTypeCode
            (Integer serialNumber,String birthDeath);
}
