package com.nhnacademy.certificate.domain.entitydto;

import java.time.LocalDateTime;

public interface ResidentDto {
    Integer getResidentSerialNumber();
    String getName();
    String getResidentRegistrationNumber();
    String getGenderCode();
    LocalDateTime getBirthDate();
    String getBirthPlaceCode();
    String getRegistrationBaseAddress();
    LocalDateTime getDeathDate();
    String getDeathPlaceCode();
    String getDeathPlaceAddress();

}
