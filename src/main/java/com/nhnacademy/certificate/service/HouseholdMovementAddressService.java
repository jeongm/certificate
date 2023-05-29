package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.domain.viewdto.HouseholdMovementAddressDto;
import com.nhnacademy.certificate.domain.requestdto.HouseholdMovementAddressRegisterRequest;
import com.nhnacademy.certificate.domain.requestdto.HouseholdMovementAddressUpdateRequest;
import com.nhnacademy.certificate.entity.HouseholdMovementAddress;
import com.nhnacademy.certificate.exception.HouseholdMovementAddressNotFoundException;
import com.nhnacademy.certificate.repository.HouseholdMovementAddressRepository;
import com.nhnacademy.certificate.repository.HouseholdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class HouseholdMovementAddressService {

    private final HouseholdMovementAddressRepository householdMovementAddressRepository;
    private final HouseholdRepository householdRepository;

    public void registerHouseholdMovementAddress(Integer householdSerialNumber,
                                                 HouseholdMovementAddressRegisterRequest householdMovementAddressRequest){
        HouseholdMovementAddress newHouseholdMovementAddress = HouseholdMovementAddress.builder()
                .householdMovementAddressPk(new HouseholdMovementAddress.HouseholdMovementAddressPk
                        (householdSerialNumber,householdMovementAddressRequest.getHouseMovementReportDate()))
                .houseMovementAddress(householdMovementAddressRequest.getHouseMovementAddress())
                .lastAddressYn(householdMovementAddressRequest.getLastAddressYn())
                .household(householdRepository.getReferenceById(householdSerialNumber))
                .build();
        householdMovementAddressRepository.save(newHouseholdMovementAddress);
    }
    public void updateHouseholdMovementAddress(Integer householdSerialNumber,
                                               LocalDate reportDate,
                                               @Valid HouseholdMovementAddressUpdateRequest householdMovementAddressRequest) {
        HouseholdMovementAddressDto householdMovementAddressDto = householdMovementAddressRepository.findByHouseholdMovementAddressPk
                (new HouseholdMovementAddress.HouseholdMovementAddressPk(householdSerialNumber,reportDate));

        if(Objects.isNull(householdMovementAddressDto)) {
            throw new HouseholdMovementAddressNotFoundException();
        }

        HouseholdMovementAddress updateHouseholdMovementAddress = HouseholdMovementAddress.builder()
                .householdMovementAddressPk(new HouseholdMovementAddress.HouseholdMovementAddressPk
                        (householdSerialNumber,reportDate))
                .houseMovementAddress(Objects.isNull(householdMovementAddressRequest.getHouseMovementAddress())
                        ? householdMovementAddressDto.getHouseMovementAddress()
                        : householdMovementAddressRequest.getHouseMovementAddress())
                .lastAddressYn(Objects.isNull(householdMovementAddressRequest.getLastAddressYn())
                        ? householdMovementAddressDto.getLastAddressYn()
                        : householdMovementAddressRequest.getLastAddressYn())
                .build();
        householdMovementAddressRepository.save(updateHouseholdMovementAddress);
    }
    public void deleteHouseholdMovementAddress(Integer residentSerialNumber,
                                               LocalDate reportDate){
        householdMovementAddressRepository.deleteById(new HouseholdMovementAddress.HouseholdMovementAddressPk(residentSerialNumber,reportDate));
    }
}
