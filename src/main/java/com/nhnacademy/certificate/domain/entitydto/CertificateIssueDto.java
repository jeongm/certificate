package com.nhnacademy.certificate.domain.entitydto;

import com.nhnacademy.certificate.entity.Resident;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

public interface CertificateIssueDto {

    Long getCertificateConfirmationNumber();
    Resident getResident();

    String getCertificateTypeCode();

    LocalDate getCertificateIssueDate();
}
