package com.nhnacademy.certificate.domain.viewdto;

import java.time.LocalDate;

public interface HouseholdResidentsDto {
    String getHouseholdRelationshipCode();
    String getGetResidentName();
    String getResidentRegistrationNumber();
    LocalDate getReportDate();
    String getHouseholdCompositionChangeReasonCode();
}
