package com.nhnacademy.certificate.domain.viewdto;

import com.nhnacademy.certificate.domain.entitydto.HouseholdMovementAddressDto;
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
    private List<HouseholdMovementAddressDto> householdMovementAddresses;
    private List<HouseholdResidentsDto> householdResidents;



    // TODO 세대에 사는 사람들 리스트로 만들자 타입하나 만들어서
//    private String householdRelationshipCode;
//    private String residentName;
//    private String residentRegistrationNumber;
//    private LocalDate reportDate;
//    private String householdCompositionChangeReasonCode;


}
