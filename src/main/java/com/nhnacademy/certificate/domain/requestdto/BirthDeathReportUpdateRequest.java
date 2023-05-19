package com.nhnacademy.certificate.domain.requestdto;

import lombok.Getter;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Getter
public class BirthDeathReportUpdateRequest {
    private Integer targetSerialNumber;
    private String birthDeathTypeCode;
    private String birthReportQualificationsCode;
    private String deathReportQualificationsCode;
    private LocalDate birthDeathReportDate;
    @Email
    private String emailAddress;
    private String phoneNumber;
}
