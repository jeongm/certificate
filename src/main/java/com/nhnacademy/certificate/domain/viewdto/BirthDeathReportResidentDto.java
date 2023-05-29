package com.nhnacademy.certificate.domain.viewdto;

import java.time.LocalDate;


public interface BirthDeathReportResidentDto {
    LocalDate getBirthDeathReportDate();
    ResidentDto getTargetResident();

    ReportResidentNameRegistrationNumberDto getReportResident();
    String getBirthReportQualificationsCode();
    String getDeathReportQualificationsCode();
    String getEmailAddress();
    String getPhoneNumber();

    interface ReportResidentNameRegistrationNumberDto {
        String getResidentRegistrationNumber();
        String getName();
    }

}

