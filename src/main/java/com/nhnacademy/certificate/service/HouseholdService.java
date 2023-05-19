package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.domain.requestdto.HouseholdRegisterRequest;
import com.nhnacademy.certificate.entity.Household;
import com.nhnacademy.certificate.repository.HouseholdRepository;
import org.springframework.stereotype.Service;

@Service
public class HouseholdService {

    private final HouseholdRepository householdRepository;

    public HouseholdService(HouseholdRepository householdRepository) {
        this.householdRepository = householdRepository;
    }

    public void registerHousehold(HouseholdRegisterRequest householdRegisterRequest){
        Household newHousehold = Household.builder()
                .householdCompositionDate(householdRegisterRequest.getHouseholdCompositionDate())
                .householdCompositionReasonCode(householdRegisterRequest.getHouseholdCompositionReasonCode())
                .currentHouseMovementAddress(householdRegisterRequest.getCurrentHouseMovementAddress())
                .build();

        householdRepository.saveAndFlush(newHousehold);
    }
    public void deleteHousehold(Integer householdSerialNumber){
        householdRepository.deleteById(householdSerialNumber);
        householdRepository.flush();
    }
}
