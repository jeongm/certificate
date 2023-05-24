package com.nhnacademy.certificate.domain.viewdto;

import com.nhnacademy.certificate.domain.entitydto.HouseholdMovementAddressDto;
import com.nhnacademy.certificate.domain.entitydto.ResidentDto;

import java.util.List;

public interface HouseholdDto {
    HouseholdResidentAddressCompositionDto getHousehold();

    interface HouseholdResidentAddressCompositionDto{
        ResidentDto getResident();
        List<HouseholdMovementAddressDto> getHouseholdMovementAddresses();
        List<HouseholdResidentsDto> getHouseholdCompositionResidents();
    }
}
