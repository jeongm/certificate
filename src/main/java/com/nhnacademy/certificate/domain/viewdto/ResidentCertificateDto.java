package com.nhnacademy.certificate.domain.viewdto;

import com.nhnacademy.certificate.domain.entitydto.HouseholdMovementAddressDto;
import com.nhnacademy.certificate.domain.entitydto.ResidentDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class ResidentCertificateDto {
    private Long certificateConfirmationNumber;
    private LocalDate issueDate;
    // 기준 주민을 통해 세대 뽑아서 세대 기록이랑, 세대구성주민
    private ResidentDto resident;
    private List<HouseholdMovementAddressDto> householdMovementAddresses;
    private List<HouseholdResidentsDto> householdResidents;





}
