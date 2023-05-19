package com.nhnacademy.certificate.domain.entitydto;

import com.nhnacademy.certificate.entity.BirthDeathReportResident;

import java.time.LocalDate;

public interface BirthDeathReportResidentDto {
    BirthDeathReportResident.BirthDeathReportResidentPk getBirthDeathReportResidentPk();
    LocalDate getBirthDeathReportDate();
    String getBirthReportQualificationsCode();
    String getDeathReportQualificationsCode();
    String getEmailAddress();
    String getPhoneNumber();

}
