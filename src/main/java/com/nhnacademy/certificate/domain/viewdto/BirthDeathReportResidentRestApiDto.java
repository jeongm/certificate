package com.nhnacademy.certificate.domain.viewdto;

import com.nhnacademy.certificate.entity.BirthDeathReportResident;

import java.time.LocalDate;

public interface BirthDeathReportResidentRestApiDto {
    BirthDeathReportResident.BirthDeathReportResidentPk getBirthDeathReportResidentPk();
    LocalDate getBirthDeathReportDate();
    String getBirthReportQualificationsCode();
    String getDeathReportQualificationsCode();
    String getEmailAddress();
    String getPhoneNumber();

}
