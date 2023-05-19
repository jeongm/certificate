package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.domain.entitydto.BirthDeathReportResidentDto;
import com.nhnacademy.certificate.domain.requestdto.BirthDeathReportRegisterRequest;
import com.nhnacademy.certificate.entity.BirthDeathReportResident;
import com.nhnacademy.certificate.exception.ResidentNotFoundException;
import com.nhnacademy.certificate.repository.BirthDeathReportResidentRepository;
import com.nhnacademy.certificate.repository.ResidentRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

        birthDeathReportResidentRepository.saveAndFlush(newBirthReport);
    }
    public void updateBirthReport(Integer reportResidentSerialNumber,
                                  Integer targetSerialNumber,
                                  BirthDeathReportRegisterRequest birthReportRegisterRequest){
        String birthDeathTypeCode = "출생";

        BirthDeathReportResidentDto birthReportResidentDto = birthDeathReportResidentRepository.findByBirthDeathReportResidentPk
                (new BirthDeathReportResident.BirthDeathReportResidentPk(targetSerialNumber,birthDeathTypeCode,reportResidentSerialNumber));

        if(Objects.isNull(birthReportResidentDto)) {
            throw new ResidentNotFoundException();
        }

        BirthDeathReportResident updateBirthReport = BirthDeathReportResident.builder()
                .birthDeathReportResidentPk(birthReportResidentDto.getBirthDeathReportResidentPk())
                .birthDeathReportDate(Objects.isNull(birthReportRegisterRequest.getBirthDeathReportDate())
                        ? birthReportResidentDto.getBirthDeathReportDate() : birthReportRegisterRequest.getBirthDeathReportDate())
                .birthReportQualificationsCode(Objects.isNull(birthReportRegisterRequest.getBirthReportQualificationsCode())
                        ? birthReportResidentDto.getBirthReportQualificationsCode(): birthReportRegisterRequest.getBirthReportQualificationsCode())
                .emailAddress(Objects.isNull(birthReportRegisterRequest.getEmailAddress())
                        ? birthReportResidentDto.getEmailAddress() : birthReportRegisterRequest.getEmailAddress())
                .phoneNumber(Objects.isNull(birthReportRegisterRequest.getPhoneNumber())
                        ? birthReportResidentDto.getPhoneNumber(): birthReportRegisterRequest.getPhoneNumber())
                .reportResident(residentRepository.getReferenceById(reportResidentSerialNumber))
                .targetResident(residentRepository.getReferenceById(targetSerialNumber))
                .build();

        birthDeathReportResidentRepository.saveAndFlush(updateBirthReport);

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

        birthDeathReportResidentRepository.saveAndFlush(newBirthReport);

    }
    public void updateDeathReport(Integer reportResidentSerialNumber,
                                  Integer targetSerialNumber,
                                  BirthDeathReportRegisterRequest birthReportRegisterRequest){
        String birthDeathTypeCode = "사망";

        BirthDeathReportResidentDto deathReportResidentDto = birthDeathReportResidentRepository.findByBirthDeathReportResidentPk
                (new BirthDeathReportResident.BirthDeathReportResidentPk(targetSerialNumber,birthDeathTypeCode,reportResidentSerialNumber));

        if(Objects.isNull(deathReportResidentDto)) {
            throw new ResidentNotFoundException();
        }

        BirthDeathReportResident updateDeathReport = BirthDeathReportResident.builder()
                .birthDeathReportResidentPk(deathReportResidentDto.getBirthDeathReportResidentPk())
                .birthDeathReportDate(Objects.isNull(birthReportRegisterRequest.getBirthDeathReportDate())
                        ? deathReportResidentDto.getBirthDeathReportDate() : birthReportRegisterRequest.getBirthDeathReportDate())
                .birthReportQualificationsCode(Objects.isNull(birthReportRegisterRequest.getBirthReportQualificationsCode())
                        ? deathReportResidentDto.getDeathReportQualificationsCode() : birthReportRegisterRequest.getBirthReportQualificationsCode())
                .emailAddress(Objects.isNull(birthReportRegisterRequest.getEmailAddress())
                        ? deathReportResidentDto.getEmailAddress() : birthReportRegisterRequest.getEmailAddress())
                .phoneNumber(Objects.isNull(birthReportRegisterRequest.getPhoneNumber())
                        ? deathReportResidentDto.getPhoneNumber(): birthReportRegisterRequest.getPhoneNumber())
                .reportResident(residentRepository.getReferenceById(reportResidentSerialNumber))
                .targetResident(residentRepository.getReferenceById(targetSerialNumber))
                .build();

        birthDeathReportResidentRepository.saveAndFlush(updateDeathReport);

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
}
