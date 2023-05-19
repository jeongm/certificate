package com.nhnacademy.certificate.domain.requestdto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class HouseholdMovementAddressUpdateRequest {
    @NotNull
    private LocalDate houseMovementReportDate;
    @NotBlank
    private String houseMovementAddress;
    @NotBlank
    private String lastAddressYn;
}
