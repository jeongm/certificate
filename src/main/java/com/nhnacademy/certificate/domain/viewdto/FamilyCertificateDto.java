package com.nhnacademy.certificate.domain.viewdto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Builder
public class FamilyCertificateDto {
    private LocalDate certificateIssueDate;
    private Long certificateConfirmationNumber;
    private ResidentDto baseResident;
    private List<FamilyResidentDto> familyResident;
}
