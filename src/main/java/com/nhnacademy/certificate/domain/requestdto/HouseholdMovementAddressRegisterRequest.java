package com.nhnacademy.certificate.domain.requestdto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class HouseholdMovementAddressRegisterRequest {
    private LocalDate houseMovementReportDate;
    private String houseMovementAddress;
    private String lastAddressYn;
}
