package com.nhnacademy.certificate.domain.entitydto;

import com.nhnacademy.certificate.entity.FamilyRelationship;
import com.nhnacademy.certificate.entity.Resident;

public interface FamilyRelationshipDto {
    FamilyRelationship.FamilyRelationshipPk getfamilyRelationshipPk();
    String getFamilyRelationshipCode();
    ResidentDto getBaseResident();
    ResidentDto getTargetResident();


}
