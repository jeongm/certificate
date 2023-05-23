package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.domain.requestdto.HouseholdRegisterRequest;
import com.nhnacademy.certificate.entity.Household;
import com.nhnacademy.certificate.repository.HouseholdRepository;
import com.nhnacademy.certificate.repository.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HouseholdService {

    private final HouseholdRepository householdRepository;
    private final ResidentRepository residentRepository;

    public HouseholdService(HouseholdRepository householdRepository, ResidentRepository residentRepository) {
        this.householdRepository = householdRepository;
        this.residentRepository = residentRepository;
    }


    public void registerHousehold(HouseholdRegisterRequest householdRegisterRequest){
        Household newHousehold = Household.builder()
                .householdCompositionDate(householdRegisterRequest.getHouseholdCompositionDate())
                .householdCompositionReasonCode(householdRegisterRequest.getHouseholdCompositionReasonCode())
                .currentHouseMovementAddress(householdRegisterRequest.getCurrentHouseMovementAddress())
                .resident(residentRepository.getReferenceById(householdRegisterRequest.getHouseholdResidentSerialNumber()))
                .build();

        householdRepository.save(newHousehold);
    }
    public void deleteHousehold(Integer householdSerialNumber){
        householdRepository.deleteById(householdSerialNumber);
    }
}
