package com.nhnacademy.certificate.domain.entitydto;

import com.nhnacademy.certificate.domain.viewdto.HouseholdCompositionResidentsDto;

import java.time.LocalDate;
import java.util.List;

public interface HouseholdDto{
    ResidentDto getResident();
    LocalDate getHouseholdCompositionDate();
    String getHouseholdCompositionReasonCode();
//    String getCurrentHouseMovementAddress();
    List<HouseholdMovementAddressDto> getHouseholdMovementAddresses();
    List<HouseholdCompositionResidentsDto> getHouseholdCompositionResidents();
}
