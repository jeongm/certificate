package com.nhnacademy.certificate.domain.viewdto;


import lombok.Getter;

@Getter
public class ResidentNameRegistrationNumberDto {
    private String name;
    private String residentRegistrationNumber;

    public ResidentNameRegistrationNumberDto(String name, String residentRegistrationNumber) {
        this.name = name;
        this.residentRegistrationNumber = residentRegistrationNumber;
    }

}
