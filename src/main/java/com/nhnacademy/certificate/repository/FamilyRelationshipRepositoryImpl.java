package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.domain.viewdto.ResidentNameRegistrationNumberDto;
import com.nhnacademy.certificate.entity.FamilyRelationship;
import com.nhnacademy.certificate.entity.QFamilyRelationship;
import com.nhnacademy.certificate.entity.QResident;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class FamilyRelationshipRepositoryImpl extends QuerydslRepositorySupport implements FamilyRelationshipRepositoryCustom {
    public FamilyRelationshipRepositoryImpl() {
        super(FamilyRelationship.class);
    }


    @Override
    public ResidentNameRegistrationNumberDto findFamilyResident(Integer baseResidentSerialNumber, String relationType) {
        QFamilyRelationship familyRelationship= QFamilyRelationship.familyRelationship;
        QResident targetResident = QResident.resident;

        return from(familyRelationship)
                .innerJoin(familyRelationship.targetResident, targetResident)
                .where(familyRelationship.familyRelationshipPk.baseResidentSerialNumber.eq(baseResidentSerialNumber))
                .where(familyRelationship.familyRelationshipCode.eq(relationType))
                .select(Projections.constructor(ResidentNameRegistrationNumberDto.class,
                        targetResident.name.as("name"),
                        targetResident.residentRegistrationNumber.as("residentRegistrationNumber")))
                .fetchOne();
    }
}
