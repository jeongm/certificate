package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.domain.entitydto.ResidentDto;
import com.nhnacademy.certificate.domain.entitydto.ResidentSerialNumberAndNameDto;
import com.nhnacademy.certificate.domain.requestdto.ResidentRegisterRequest;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.exception.ResidentNotFoundException;
import com.nhnacademy.certificate.repository.ResidentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public void updateResident(Integer residentSerialNumber, ResidentRegisterRequest residentRegisterRequest) {
        if (Objects.isNull(residentRepository.getReferenceById(residentSerialNumber))) {
            throw new ResidentNotFoundException();
        }

        ResidentDto residentDto =  residentRepository.findByResidentSerialNumber(residentSerialNumber);

        Resident updateResident = Resident.builder()
                .residentSerialNumber(residentSerialNumber)
                .name(Objects.isNull(residentRegisterRequest.getName())
                        ? residentDto.getName() : residentRegisterRequest.getName())
                .residentRegistrationNumber(Objects.isNull(residentRegisterRequest.getResidentRegistrationNumber())
                        ? residentDto.getResidentRegistrationNumber() : residentRegisterRequest.getResidentRegistrationNumber())
                .genderCode(Objects.isNull(residentRegisterRequest.getGenderCode())
                        ? residentDto.getGenderCode() : residentRegisterRequest.getGenderCode())
                .birthDate(Objects.isNull(residentRegisterRequest.getBirthDate())
                        ? residentDto.getBirthDate() : residentRegisterRequest.getBirthDate())
                .birthPlaceCode(Objects.isNull(residentRegisterRequest.getBirthPlaceCode())
                        ? residentDto.getBirthPlaceCode() : residentRegisterRequest.getBirthPlaceCode())
                .registrationBaseAddress(Objects.isNull(residentRegisterRequest.getRegistrationBaseAddress())
                        ? residentDto.getRegistrationBaseAddress() : residentRegisterRequest.getRegistrationBaseAddress())
                .deathDate(Objects.isNull(residentRegisterRequest.getDeathDate())
                        ? residentDto.getDeathDate() : residentRegisterRequest.getDeathDate())
                .deathPlaceCode(Objects.isNull(residentRegisterRequest.getDeathPlaceCode())
                        ? residentDto.getDeathPlaceCode() : residentRegisterRequest.getDeathPlaceCode())
                .deathPlaceAddress(Objects.isNull(residentRegisterRequest.getDeathPlaceAddress())
                        ? residentDto.getDeathPlaceAddress() : residentRegisterRequest.getDeathPlaceAddress())
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
