package com.nhnacademy.certificate.domain.viewdto;

import com.nhnacademy.certificate.domain.viewdto.ResidentDto;
import com.nhnacademy.certificate.entity.FamilyRelationship;

public interface FamilyRelationshipDto {
    FamilyRelationship.FamilyRelationshipPk getfamilyRelationshipPk();
    String getFamilyRelationshipCode();
    ResidentDto getBaseResident();
    ResidentDto getTargetResident();


}
