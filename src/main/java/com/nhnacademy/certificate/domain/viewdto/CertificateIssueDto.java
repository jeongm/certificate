package com.nhnacademy.certificate.domain.viewdto;

import com.nhnacademy.certificate.entity.Resident;

import java.time.LocalDate;

public interface CertificateIssueDto {

    Long getCertificateConfirmationNumber();
    Resident getResident();

    String getCertificateTypeCode();

    LocalDate getCertificateIssueDate();
}
