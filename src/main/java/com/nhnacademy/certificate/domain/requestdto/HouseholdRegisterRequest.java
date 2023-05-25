package com.nhnacademy.certificate.domain.requestdto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class HouseholdRegisterRequest {
    @NotNull
    private LocalDate householdCompositionDate;
    @NotBlank
    private String householdCompositionReasonCode;
    @NotBlank
    private String currentHouseMovementAddress;
    @NotNull
    private Integer householdResidentSerialNumber;
}
