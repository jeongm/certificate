package com.nhnacademy.certificate.domain.viewdto;

import com.nhnacademy.certificate.domain.viewdto.HouseholdCompositionResidentsDto;
import com.nhnacademy.certificate.domain.viewdto.HouseholdMovementAddressDto;
import com.nhnacademy.certificate.domain.viewdto.ResidentDto;

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
