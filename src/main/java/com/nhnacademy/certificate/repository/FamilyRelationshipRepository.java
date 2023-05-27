package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.domain.restviewdto.FamilyRelationshipDto;
import com.nhnacademy.certificate.entity.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, FamilyRelationship.FamilyRelationshipPk>, FamilyRelationshipRepositoryCustom {
    FamilyRelationshipDto findByFamilyRelationshipPk(FamilyRelationship.FamilyRelationshipPk familyRelationshipPk);

}
