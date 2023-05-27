package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.domain.restviewdto.BirthDeathReportResidentDto;
import com.nhnacademy.certificate.domain.requestdto.BirthDeathReportRegisterRequest;
import com.nhnacademy.certificate.domain.requestdto.BirthDeathReportUpdateRequest;
import com.nhnacademy.certificate.entity.BirthDeathReportResident;
import com.nhnacademy.certificate.exception.ResidentNotFoundException;
import com.nhnacademy.certificate.repository.BirthDeathReportResidentRepository;
import com.nhnacademy.certificate.repository.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Objects;

@Transactional
@Service
public class BirthDeathReportResidentService {

    private final BirthDeathReportResidentRepository birthDeathReportResidentRepository;
    private final ResidentRepository residentRepository;

    public BirthDeathReportResidentService(BirthDeathReportResidentRepository birthDeathReportResidentRepository, ResidentRepository residentRepository) {
        this.birthDeathReportResidentRepository = birthDeathReportResidentRepository;
        this.residentRepository = residentRepository;
    }


    public void registerBirthReport(Integer reportResidentSerialNumber,
                                    BirthDeathReportRegisterRequest birthReportRegisterRequest){
        String birthDeathTypeCode = "출생";

        BirthDeathReportResident newBirthReport = BirthDeathReportResident.builder()
                .birthDeathReportResidentPk(new BirthDeathReportResident.BirthDeathReportResidentPk
                        (birthReportRegisterRequest.getTargetSerialNumber(),birthDeathTypeCode,reportResidentSerialNumber))
                .birthDeathReportDate(birthReportRegisterRequest.getBirthDeathReportDate())
                .birthReportQualificationsCode(birthReportRegisterRequest.getBirthReportQualificationsCode())
                .emailAddress(birthReportRegisterRequest.getEmailAddress())
                .phoneNumber(birthReportRegisterRequest.getPhoneNumber())
                .reportResident(residentRepository.getReferenceById(reportResidentSerialNumber))
                .targetResident(residentRepository.getReferenceById(birthReportRegisterRequest.getTargetSerialNumber()))
                .build();

        birthDeathReportResidentRepository.save(newBirthReport);
    }
    public void updateBirthReport(Integer reportResidentSerialNumber,
                                  Integer targetSerialNumber,
                                  @Valid BirthDeathReportUpdateRequest birthDeathReportRequest){
        String birthDeathTypeCode = "출생";

        BirthDeathReportResidentDto birthReportResidentDto = birthDeathReportResidentRepository.findByBirthDeathReportResidentPk
                (new BirthDeathReportResident.BirthDeathReportResidentPk(targetSerialNumber,birthDeathTypeCode,reportResidentSerialNumber));

        if(Objects.isNull(birthReportResidentDto)) {
            throw new ResidentNotFoundException();
        }

        BirthDeathReportResident updateBirthReport = BirthDeathReportResident.builder()
                .birthDeathReportResidentPk(birthReportResidentDto.getBirthDeathReportResidentPk())
                .birthDeathReportDate(Objects.isNull(birthDeathReportRequest.getBirthDeathReportDate())
                        ? birthReportResidentDto.getBirthDeathReportDate() : birthDeathReportRequest.getBirthDeathReportDate())
                .birthReportQualificationsCode(Objects.isNull(birthDeathReportRequest.getBirthReportQualificationsCode())
                        ? birthReportResidentDto.getBirthReportQualificationsCode(): birthDeathReportRequest.getBirthReportQualificationsCode())
                .emailAddress(Objects.isNull(birthDeathReportRequest.getEmailAddress())
                        ? birthReportResidentDto.getEmailAddress() : birthDeathReportRequest.getEmailAddress())
                .phoneNumber(Objects.isNull(birthDeathReportRequest.getPhoneNumber())
                        ? birthReportResidentDto.getPhoneNumber(): birthDeathReportRequest.getPhoneNumber())
                .reportResident(residentRepository.getReferenceById(reportResidentSerialNumber))
                .targetResident(residentRepository.getReferenceById(targetSerialNumber))
                .build();

        birthDeathReportResidentRepository.save(updateBirthReport);

    }
    public void deleteBirthReport(Integer reportResidentSerialNumber,
                                  Integer targetSerialNumber){
        String birthDeathTypeCode = "출생";
        if (birthDeathReportResidentRepository.existsById(new BirthDeathReportResident.BirthDeathReportResidentPk
                (targetSerialNumber,birthDeathTypeCode, reportResidentSerialNumber))) {
        birthDeathReportResidentRepository.deleteById
                (new BirthDeathReportResident.BirthDeathReportResidentPk
                        (targetSerialNumber,birthDeathTypeCode, reportResidentSerialNumber));
        }
    }


    public void registerDeathReport(Integer reportResidentSerialNumber,
                                    BirthDeathReportRegisterRequest birthReportRegisterRequest){
        String birthDeathTypeCode = "출생";

        BirthDeathReportResident newBirthReport = BirthDeathReportResident.builder()
                .birthDeathReportResidentPk(new BirthDeathReportResident.BirthDeathReportResidentPk
                        (birthReportRegisterRequest.getTargetSerialNumber(),birthDeathTypeCode,reportResidentSerialNumber))
                .birthDeathReportDate(birthReportRegisterRequest.getBirthDeathReportDate())
                .deathReportQualificationsCode(birthReportRegisterRequest.getBirthReportQualificationsCode())
                .emailAddress(birthReportRegisterRequest.getEmailAddress())
                .phoneNumber(birthReportRegisterRequest.getPhoneNumber())
                .reportResident(residentRepository.getReferenceById(reportResidentSerialNumber))
                .targetResident(residentRepository.getReferenceById(birthReportRegisterRequest.getTargetSerialNumber()))

                .build();

        birthDeathReportResidentRepository.save(newBirthReport);

    }
    public void updateDeathReport(Integer reportResidentSerialNumber,
                                  Integer targetSerialNumber,
                                  @Valid BirthDeathReportUpdateRequest birthDeathReportRequest){
        String birthDeathTypeCode = "사망";

        BirthDeathReportResidentDto deathReportResidentDto = birthDeathReportResidentRepository.findByBirthDeathReportResidentPk
                (new BirthDeathReportResident.BirthDeathReportResidentPk(targetSerialNumber,birthDeathTypeCode,reportResidentSerialNumber));

        if(Objects.isNull(deathReportResidentDto)) {
            throw new ResidentNotFoundException();
        }

        BirthDeathReportResident updateDeathReport = BirthDeathReportResident.builder()
                .birthDeathReportResidentPk(deathReportResidentDto.getBirthDeathReportResidentPk())
                .birthDeathReportDate(Objects.isNull(birthDeathReportRequest.getBirthDeathReportDate())
                        ? deathReportResidentDto.getBirthDeathReportDate() : birthDeathReportRequest.getBirthDeathReportDate())
                .birthReportQualificationsCode(Objects.isNull(birthDeathReportRequest.getBirthReportQualificationsCode())
                        ? deathReportResidentDto.getDeathReportQualificationsCode() : birthDeathReportRequest.getBirthReportQualificationsCode())
                .emailAddress(Objects.isNull(birthDeathReportRequest.getEmailAddress())
                        ? deathReportResidentDto.getEmailAddress() : birthDeathReportRequest.getEmailAddress())
                .phoneNumber(Objects.isNull(birthDeathReportRequest.getPhoneNumber())
                        ? deathReportResidentDto.getPhoneNumber(): birthDeathReportRequest.getPhoneNumber())
                .reportResident(residentRepository.getReferenceById(reportResidentSerialNumber))
                .targetResident(residentRepository.getReferenceById(targetSerialNumber))
                .build();

        birthDeathReportResidentRepository.save(updateDeathReport);

    }
    public void deleteDeathReport(Integer reportResidentSerialNumber,
                                  Integer targetSerialNumber){
        String birthDeathTypeCode = "사망";
        if (birthDeathReportResidentRepository.existsById(new BirthDeathReportResident.BirthDeathReportResidentPk
                (targetSerialNumber,birthDeathTypeCode, reportResidentSerialNumber))) {
            birthDeathReportResidentRepository.deleteById
                    (new BirthDeathReportResident.BirthDeathReportResidentPk
                            (targetSerialNumber,birthDeathTypeCode, reportResidentSerialNumber));
        }
    }

    public boolean exitsBirthReport(Integer targetSerialNumber){
        return birthDeathReportResidentRepository.existsByTargetResident_ResidentSerialNumberAndBirthDeathReportResidentPk_BirthDeathTypeCode(targetSerialNumber, "출생");
    }

    public boolean exitsDeathReport(Integer targetSerialNumber){
        return birthDeathReportResidentRepository.existsByTargetResident_ResidentSerialNumberAndBirthDeathReportResidentPk_BirthDeathTypeCode(targetSerialNumber, "사망");
    }



}
