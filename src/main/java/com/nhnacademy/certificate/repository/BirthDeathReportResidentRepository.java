package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.domain.viewdto.BirthDeathReportResidentRestApiDto;
import com.nhnacademy.certificate.domain.viewdto.BirthDeathReportResidentDto;
import com.nhnacademy.certificate.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthDeathReportResidentRepository extends JpaRepository<BirthDeathReportResident, BirthDeathReportResident.BirthDeathReportResidentPk> {
    BirthDeathReportResidentRestApiDto findByBirthDeathReportResidentPk(BirthDeathReportResident.BirthDeathReportResidentPk birthDeathReportResidentPk);

    boolean existsByTargetResident_ResidentSerialNumberAndBirthDeathReportResidentPk_BirthDeathTypeCode
            (Integer serialNumber,String birthDeath);

    BirthDeathReportResidentDto findByBirthDeathReportResidentPk_ResidentSerialNumberAndBirthDeathReportResidentPk_BirthDeathTypeCode(Integer targetResident, String type);
}
