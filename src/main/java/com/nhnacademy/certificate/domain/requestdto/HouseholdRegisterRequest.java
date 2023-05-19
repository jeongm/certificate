package com.nhnacademy.certificate.domain.requestdto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class HouseholdRegisterRequest {
    private LocalDate householdCompositionDate;
    private String householdCompositionReasonCode;
    private String currentHouseMovementAddress;
}
