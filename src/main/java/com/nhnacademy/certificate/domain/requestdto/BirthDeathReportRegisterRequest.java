package com.nhnacademy.certificate.domain.requestdto;

import lombok.*;

import java.time.LocalDate;

@Getter
public class BirthDeathReportRegisterRequest {
    private Integer targetSerialNumber;
    private String birthDeathTypeCode;
    private LocalDate birthDeathReportDate;
    private String birthReportQualificationsCode;
    private String deathReportQualificationsCode;
    private String emailAddress;
    private String phoneNumber;
}
