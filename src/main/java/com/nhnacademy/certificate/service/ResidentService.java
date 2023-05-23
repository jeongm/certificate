package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.domain.entitydto.ResidentDto;
import com.nhnacademy.certificate.domain.requestdto.ResidentRegisterRequest;
import com.nhnacademy.certificate.domain.requestdto.ResidentUpdateRequest;
import com.nhnacademy.certificate.domain.viewdto.ResidentNumberNameReportDto;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.exception.ResidentNotFoundException;
import com.nhnacademy.certificate.repository.BirthDeathReportResidentRepository;
import com.nhnacademy.certificate.repository.ResidentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ResidentService {

    private ResidentRepository residentRepository;
    private BirthDeathReportResidentRepository birthDeathReportResidentRepository;

    public ResidentService(ResidentRepository residentRepository, BirthDeathReportResidentRepository birthDeathReportResidentRepository) {
        this.residentRepository = residentRepository;
        this.birthDeathReportResidentRepository = birthDeathReportResidentRepository;
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
        residentRepository.save(newResident);
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

        residentRepository.save(updateResident);

    }

    public void deleteResident(Integer residentSerialNumber) {
        if (Objects.isNull(residentRepository.getReferenceById(residentSerialNumber))) {
            throw new ResidentNotFoundException();
        }
        residentRepository.deleteById(residentSerialNumber);
    }

    /**
     * 주민목록, 출생 사망 증명서는 있을시 보여줌
     * @param pageable
     * @return
     */
    public Page<ResidentNumberNameReportDto> getResidents(Pageable pageable) {

        Page<ResidentDto> result = residentRepository.getAllBy(pageable);

        List<ResidentNumberNameReportDto> residents = new ArrayList<>();
        for (ResidentDto r : result.getContent()){
            ResidentNumberNameReportDto residentNumberNameReport = ResidentNumberNameReportDto.builder()
                    .residentSerialNumber(r.getResidentSerialNumber())
                    .name(r.getName())
                    .isBirthReport
                            (birthDeathReportResidentRepository.existsByTargetResident_ResidentSerialNumberAndBirthDeathReportResidentPk_BirthDeathTypeCode
                                    (r.getResidentSerialNumber(), "출생"))
                    .isDeathReport
                            (birthDeathReportResidentRepository.existsByTargetResident_ResidentSerialNumberAndBirthDeathReportResidentPk_BirthDeathTypeCode
                                    (r.getResidentSerialNumber(), "사망"))
                    .build();
            residents.add(residentNumberNameReport);
        }

        Page<ResidentNumberNameReportDto> residentsPage = new PageImpl<>(residents, pageable, result.getTotalElements());

        return residentsPage;
    }

}
