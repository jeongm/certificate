package com.nhnacademy.certificate.domain.viewdto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class BirthCertificateDto {
    private Long certificateConfirmationNumber;
    private LocalDate issueDate;

}
