package com.nhnacademy.certificate.domain.requestdto;


import lombok.Getter;

@Getter
public class FamilyRelationshipUpdateRequest {
    private Integer familySerialNumber;
    private String relationShip;
}
