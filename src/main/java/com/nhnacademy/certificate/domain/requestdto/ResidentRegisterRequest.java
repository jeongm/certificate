package com.nhnacademy.certificate.domain.requestdto;


import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class ResidentRegisterRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String residentRegistrationNumber;
    @NotBlank
    private String genderCode;
    @NotNull
    private LocalDateTime birthDate;
    @NotBlank
    private String birthPlaceCode;
    @NotBlank
    private String registrationBaseAddress;
    private LocalDateTime deathDate;
    private String deathPlaceCode;
    private String deathPlaceAddress;
}
