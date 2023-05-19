package com.nhnacademy.certificate.domain.requestdto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResidentUpdateRequest {
    private String name;
    private String residentRegistrationNumber;
    private String genderCode;
    private LocalDateTime birthDate;
    private String birthPlaceCode;
    private String registrationBaseAddress;
    private LocalDateTime deathDate;
    private String deathPlaceCode;
    private String deathPlaceAddress;
}
