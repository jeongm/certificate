package com.nhnacademy.certificate.domain;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FamilyRelationshipRegisterRequest {
    @NotNull
    private Integer familySerialNumber;
    @NotNull
    private String familyRelationshipCode;
}
