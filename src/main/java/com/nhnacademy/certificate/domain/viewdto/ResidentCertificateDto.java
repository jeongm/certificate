package com.nhnacademy.certificate.domain.viewdto;

import com.nhnacademy.certificate.domain.restviewdto.HouseholdMovementAddressDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class ResidentCertificateDto {
    private Long certificateConfirmationNumber;
    private LocalDate issueDate;
    private String householdResidentName;

    private String householdCompositionReasonCode;
    private LocalDate householdCompositionDate;
//    private String currentHouseMovementAddress;

    private List<HouseholdMovementAddressDto> householdMovementAddresses;
    private List<HouseholdCompositionResidentsDto> householdResidents;





}
