package com.nhnacademy.certificate.domain.entitydto;

import com.nhnacademy.certificate.entity.Household;
import com.nhnacademy.certificate.entity.HouseholdMovementAddress;

public interface HouseholdMovementAddressDto {
    HouseholdMovementAddress.HouseholdMovementAddressPk getHouseholdMovementAddressPk();
    String getHouseMovementAddress();
    String getLastAddressYn();
    Household getHousehold();

}
