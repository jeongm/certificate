package com.nhnacademy.certificate.domain.requestdto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class BirthDeathReportRegisterRequest {
    @NotNull
    private Integer targetSerialNumber;
    private String birthDeathTypeCode;
    @NotNull
    private LocalDate birthDeathReportDate;
    private String birthReportQualificationsCode;
    private String deathReportQualificationsCode;
    @Email
    private String emailAddress;
    @NotBlank
    private String phoneNumber;
}
