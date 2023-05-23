package com.nhnacademy.certificate.entity;

public interface HouseholdNumberDto {
    Integer getHouseholdCompositionResidentPk_HouseholdSerialNumber();
    HouseholdSerialNumber getHouseholdSerialNumber();

    interface HouseholdSerialNumber{
        Integer getHouseholdSerialNumber();
    }
}
