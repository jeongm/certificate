package com.nhnacademy.certificate.domain.viewdto;

import java.time.LocalDate;

public interface HouseholdCompositionResidentsDto {
    String getHouseholdRelationshipCode();
    HouseholdResidentDto getResident();
    LocalDate getReportDate();
    String getHouseholdCompositionChangeReasonCode();

    interface HouseholdResidentDto {
            String getName();
            String getResidentRegistrationNumber();
    }
}
