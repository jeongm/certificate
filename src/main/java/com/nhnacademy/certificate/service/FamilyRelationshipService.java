package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.domain.restviewdto.FamilyRelationshipDto;
import com.nhnacademy.certificate.domain.requestdto.FamilyRelationshipRegisterRequest;
import com.nhnacademy.certificate.domain.requestdto.FamilyRelationshipUpdateRequest;
import com.nhnacademy.certificate.entity.FamilyRelationship;
import com.nhnacademy.certificate.exception.FamilyRelationshipNotFoundException;
import com.nhnacademy.certificate.repository.FamilyRelationshipRepository;
import com.nhnacademy.certificate.repository.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Objects;

@Service
@Transactional
public class FamilyRelationshipService {

    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final ResidentRepository residentRepository;

    public FamilyRelationshipService(FamilyRelationshipRepository familyRelationshipRepository, ResidentRepository residentRepository) {
        this.familyRelationshipRepository = familyRelationshipRepository;
        this.residentRepository = residentRepository;
    }


    public void registerFamilyRelationship(Integer baseResidentSerialNumber,
                                           FamilyRelationshipRegisterRequest familyRelationshipRegisterRequest){
        FamilyRelationship newFamilyRelationship = FamilyRelationship.builder()
                .familyRelationshipPk(new FamilyRelationship.FamilyRelationshipPk
                        (baseResidentSerialNumber,familyRelationshipRegisterRequest.getFamilySerialNumber()))
                .familyRelationshipCode(familyRelationshipRegisterRequest.getRelationShip())
                .baseResident(residentRepository.getReferenceById(baseResidentSerialNumber))
                .targetResident(residentRepository.getReferenceById(familyRelationshipRegisterRequest.getFamilySerialNumber()))
                .build();
        familyRelationshipRepository.save(newFamilyRelationship);
    }
    public void updateFamilyRelationship(Integer baseResidentSerialNumber,
                                         Integer familySerialNumber,
                                         @Valid FamilyRelationshipUpdateRequest familyRelationshipRequest){
        FamilyRelationshipDto familyRelationshipDto =
                 familyRelationshipRepository.findByFamilyRelationshipPk(new FamilyRelationship.FamilyRelationshipPk(baseResidentSerialNumber,familySerialNumber));

        if(Objects.isNull(familyRelationshipDto)) {
            throw new FamilyRelationshipNotFoundException();
        }

        FamilyRelationship targetFamilyRelationship = FamilyRelationship.builder()
                .familyRelationshipPk(new FamilyRelationship.FamilyRelationshipPk(baseResidentSerialNumber,familySerialNumber))
                .familyRelationshipCode(Objects.isNull(familyRelationshipRequest.getRelationShip())
                        ? familyRelationshipDto.getFamilyRelationshipCode() : familyRelationshipRequest.getRelationShip())
                .baseResident(residentRepository.getReferenceById(familyRelationshipDto.getBaseResident().getResidentSerialNumber()))
                .targetResident(residentRepository.getReferenceById(familyRelationshipDto.getTargetResident().getResidentSerialNumber()))
                .build();
        familyRelationshipRepository.save(targetFamilyRelationship);

    }
    public void deleteFamilyRelationship(Integer baseResidentSerialNumber,
                                         Integer familySerialNumber){
        if(familyRelationshipRepository.existsById(new FamilyRelationship.FamilyRelationshipPk(baseResidentSerialNumber,familySerialNumber))) {
            familyRelationshipRepository.deleteById(new FamilyRelationship.FamilyRelationshipPk(baseResidentSerialNumber,familySerialNumber));
        }
    }
}
