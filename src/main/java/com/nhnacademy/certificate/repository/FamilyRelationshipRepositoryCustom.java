package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.domain.viewdto.ResidentNameRegistrationNumberDto;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface FamilyRelationshipRepositoryCustom {

    ResidentNameRegistrationNumberDto findFamilyResident(Integer baseResidentSerialNumber, String relationType);

}
