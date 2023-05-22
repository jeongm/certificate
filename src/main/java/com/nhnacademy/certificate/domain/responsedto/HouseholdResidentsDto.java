package com.nhnacademy.certificate.domain.responsedto;

import java.time.LocalDate;

public interface HouseholdResidentsDto {
    String getHouseholdRelationshipCode();
    String getGetResidentName();
    String getResidentRegistrationNumber();
    LocalDate getReportDate();
    String getHouseholdCompositionChangeReasonCode();
}
