package com.nhnacademy.certificate.domain.viewdto;

import java.time.LocalDate;

public interface HouseholdResidentsDto {
    String getHouseholdRelationshipCode();
    String getResidentName();
    String getResidentRegistrationNumber();
    LocalDate getReportDate();
    String getHouseholdCompositionChangeReasonCode();
}
