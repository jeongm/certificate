package com.nhnacademy.certificate.domain.viewdto;

import java.time.LocalDate;

public interface HouseholdMovementAddressDto {
    HouseholdMovementAddressPk getHouseholdMovementAddressPk();
    String getHouseMovementAddress();
    String getLastAddressYn();

    interface HouseholdMovementAddressPk{
        LocalDate getHouseMovementReportDate();

    }

}
