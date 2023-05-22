package com.nhnacademy.certificate.domain.responsedto;

import com.nhnacademy.certificate.domain.entitydto.ResidentDto;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class FamilyCertificateDto {
    private ResidentDto baseResident;
    private String registrationBaseAddress;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDate issueDate;
    private Long certificateConfirmationNumber;
    private List<FamilyResidentDto> familyResident;
}
