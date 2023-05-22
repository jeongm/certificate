package com.nhnacademy.certificate.domain.responsedto;

import java.time.LocalDateTime;


public interface FamilyResidentDto {
    String getFamilyRelationshipCode();
    String getName();
    LocalDateTime getBirthDate();
    String getResidentRegistrationNumber();
    String getGenderCode();

}

