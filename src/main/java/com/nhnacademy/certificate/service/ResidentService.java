package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.domain.entitydto.ResidentDto;
import com.nhnacademy.certificate.domain.entitydto.ResidentSerialNumberAndNameDto;
import com.nhnacademy.certificate.domain.requestdto.ResidentRegisterRequest;
import com.nhnacademy.certificate.domain.requestdto.ResidentUpdateRequest;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.exception.ResidentNotFoundException;
import com.nhnacademy.certificate.repository.ResidentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Service
public class ResidentService {

    private ResidentRepository residentRepository;

    public ResidentService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    public void registerResident(ResidentRegisterRequest residentRegisterRequest) {
        Resident newResident = Resident.builder()
                .name(residentRegisterRequest.getName())
                .residentRegistrationNumber(residentRegisterRequest.getResidentRegistrationNumber())
                .genderCode(residentRegisterRequest.getGenderCode())
                .birthDate(residentRegisterRequest.getBirthDate())
                .birthPlaceCode(residentRegisterRequest.getBirthPlaceCode())
                .registrationBaseAddress(residentRegisterRequest.getRegistrationBaseAddress())
                .deathDate(residentRegisterRequest.getDeathDate())
                .deathPlaceCode(residentRegisterRequest.getDeathPlaceCode())
                .deathPlaceAddress(residentRegisterRequest.getDeathPlaceAddress())
                .build();
        residentRepository.saveAndFlush(newResident);
    }

    public void updateResident(Integer residentSerialNumber, @Valid ResidentUpdateRequest residentUpdateRequest) {
        if (Objects.isNull(residentRepository.getReferenceById(residentSerialNumber))) {
            throw new ResidentNotFoundException();
        }

        ResidentDto residentDto =  residentRepository.findByResidentSerialNumber(residentSerialNumber);

        Resident updateResident = Resident.builder()
                .residentSerialNumber(residentSerialNumber)
                .name(Objects.isNull(residentUpdateRequest.getName())
                        ? residentDto.getName() : residentUpdateRequest.getName())
                .residentRegistrationNumber(Objects.isNull(residentUpdateRequest.getResidentRegistrationNumber())
                        ? residentDto.getResidentRegistrationNumber() : residentUpdateRequest.getResidentRegistrationNumber())
                .genderCode(Objects.isNull(residentUpdateRequest.getGenderCode())
                        ? residentDto.getGenderCode() : residentUpdateRequest.getGenderCode())
                .birthDate(Objects.isNull(residentUpdateRequest.getBirthDate())
                        ? residentDto.getBirthDate() : residentUpdateRequest.getBirthDate())
                .birthPlaceCode(Objects.isNull(residentUpdateRequest.getBirthPlaceCode())
                        ? residentDto.getBirthPlaceCode() : residentUpdateRequest.getBirthPlaceCode())
                .registrationBaseAddress(Objects.isNull(residentUpdateRequest.getRegistrationBaseAddress())
                        ? residentDto.getRegistrationBaseAddress() : residentUpdateRequest.getRegistrationBaseAddress())
                .deathDate(Objects.isNull(residentUpdateRequest.getDeathDate())
                        ? residentDto.getDeathDate() : residentUpdateRequest.getDeathDate())
                .deathPlaceCode(Objects.isNull(residentUpdateRequest.getDeathPlaceCode())
                        ? residentDto.getDeathPlaceCode() : residentUpdateRequest.getDeathPlaceCode())
                .deathPlaceAddress(Objects.isNull(residentUpdateRequest.getDeathPlaceAddress())
                        ? residentDto.getDeathPlaceAddress() : residentUpdateRequest.getDeathPlaceAddress())
                .build();

        residentRepository.saveAndFlush(updateResident);

    }

    public void deleteResident(Integer residentSerialNumber) {
        if (Objects.isNull(residentRepository.getReferenceById(residentSerialNumber))) {
            throw new ResidentNotFoundException();
        }
        residentRepository.deleteById(residentSerialNumber);
        residentRepository.flush();
    }


    public List<ResidentSerialNumberAndNameDto> getResidents(Pageable pageable) {
        return residentRepository.getAllBy(pageable).getContent();
    }

    //TODO 증명서 발급 목록
}
