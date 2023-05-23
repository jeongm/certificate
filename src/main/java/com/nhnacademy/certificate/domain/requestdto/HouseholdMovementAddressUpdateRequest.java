package com.nhnacademy.certificate.domain.requestdto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class HouseholdMovementAddressUpdateRequest {
    private LocalDate houseMovementReportDate;
    private String houseMovementAddress;
    private String lastAddressYn;
}
