package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.domain.entitydto.FamilyRelationshipDto;
import com.nhnacademy.certificate.entity.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, FamilyRelationship.FamilyRelationshipPk> {
    FamilyRelationshipDto findByFamilyRelationshipPk(FamilyRelationship.FamilyRelationshipPk familyRelationshipPk);
}
