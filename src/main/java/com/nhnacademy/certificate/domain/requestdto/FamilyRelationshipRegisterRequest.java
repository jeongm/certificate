package com.nhnacademy.certificate.domain.requestdto;


import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
public class FamilyRelationshipRegisterRequest {
    @NotNull
    private Integer familySerialNumber;
    @NotBlank
    private String relationShip;
}
