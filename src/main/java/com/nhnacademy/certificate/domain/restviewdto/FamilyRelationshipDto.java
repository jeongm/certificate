package com.nhnacademy.certificate.domain.restviewdto;

import com.nhnacademy.certificate.entity.FamilyRelationship;

public interface FamilyRelationshipDto {
    FamilyRelationship.FamilyRelationshipPk getfamilyRelationshipPk();
    String getFamilyRelationshipCode();
    ResidentDto getBaseResident();
    ResidentDto getTargetResident();


}
