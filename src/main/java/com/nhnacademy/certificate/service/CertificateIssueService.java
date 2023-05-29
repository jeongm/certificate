package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.domain.viewdto.CertificateIssueDto;
import com.nhnacademy.certificate.domain.viewdto.HouseholdMovementAddressDto;
import com.nhnacademy.certificate.domain.viewdto.*;
import com.nhnacademy.certificate.entity.CertificateIssue;
import com.nhnacademy.certificate.exception.HouseholdNotFoundException;
import com.nhnacademy.certificate.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class CertificateIssueService {
    private final CertificateIssueRepository certificateIssueRepository;
    private final ResidentRepository residentRepository;
    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;
    private final BirthDeathReportResidentRepository birthDeathReportResidentRepository;
    private final FamilyRelationshipRepository familyRelationshipRepository;



    /**
     * 증명서 발급
     * @param residentSerialNumber
     * @param certificateType 발급할 증명서 종류
     * @return 증명서 발급번호 번호
     */
    public Long createCertificate(Integer residentSerialNumber, String certificateType){
        CertificateIssue certificateIssue = CertificateIssue.builder()
                .resident(residentRepository.getReferenceById(residentSerialNumber))
                .certificateTypeCode(certificateType)
                .certificateIssueDate(LocalDate.now())
                .build();
        certificateIssueRepository.save(certificateIssue);
        return certificateIssue.getCertificateConfirmationNumber();
    }

    public CertificateIssueDto getCertificate(Long certificateIssueNumber){
        return certificateIssueRepository.findByCertificateConfirmationNumber(certificateIssueNumber);
    }


    /**
     *
     * @param residentSerialNumber
     * @return 가족관계증명서
     */
    public FamilyCertificateDto getFamilyCertificate(Integer residentSerialNumber){
        CertificateIssueDto certificateIssue= getCertificate(createCertificate(residentSerialNumber,"가족관계증명서"));

        return FamilyCertificateDto.builder()
                .certificateIssueDate(certificateIssue.getCertificateIssueDate())
                .certificateConfirmationNumber(certificateIssue.getCertificateConfirmationNumber())
                .baseResident((residentRepository.findByResidentSerialNumber(residentSerialNumber)))
                .familyResident(residentRepository.findByFamilyResident(residentSerialNumber))
                .build();

    }


    /**
     * 주민등록등본 발급
     *
     * @param residentSerialNumber
     * @return
     */
    public ResidentCertificateDto getResidentCertificate(Integer residentSerialNumber){
        CertificateIssueDto certificateIssue = getCertificate(createCertificate(residentSerialNumber,"주민등록든본"));

        HouseholdCompositionDto householdComposition = householdCompositionResidentRepository.findByResident_ResidentSerialNumber(residentSerialNumber);
        if(Objects.isNull(householdComposition)){
            throw new HouseholdNotFoundException();
        }

        return ResidentCertificateDto.builder()
                .issueDate(certificateIssue.getCertificateIssueDate())
                .certificateConfirmationNumber(certificateIssue.getCertificateConfirmationNumber())
                .householdResidentName(householdComposition.getHousehold().getResident().getName())
                .householdCompositionReasonCode(householdComposition.getHousehold().getHouseholdCompositionReasonCode())
                .householdCompositionDate(householdComposition.getHousehold().getHouseholdCompositionDate())
                .householdMovementAddresses(householdComposition
                        .getHousehold()
                        .getHouseholdMovementAddresses()
                        .stream()
                        .sorted(Comparator.comparing((HouseholdMovementAddressDto householdMovementAddressDto) ->
                                householdMovementAddressDto.getHouseholdMovementAddressPk().getHouseMovementReportDate()).reversed())
                        .collect(Collectors.toList()))
                .householdResidents(householdComposition.getHousehold().getHouseholdCompositionResidents())
                .build();

    }

    /**
     * 출생증명서
     * @param residentSerialNumber 대상자 시리얼번호
     * @return
     */
    public BirthCertificateDto getBirthCertificate(Integer residentSerialNumber){

        createCertificate(residentSerialNumber,"출생신고서");

        return BirthCertificateDto.builder()
                .birthReportResident(birthDeathReportResidentRepository
                        .findByBirthDeathReportResidentPk_ResidentSerialNumberAndBirthDeathReportResidentPk_BirthDeathTypeCode
                                (residentSerialNumber,"출생"))
                .father(familyRelationshipRepository.findFamilyResident(residentSerialNumber,"부"))// familyresident- 출생자 기준
                .mother(familyRelationshipRepository.findFamilyResident(residentSerialNumber,"모")) /// familyresident- 출생자 기준
                .build();

    }


    /**
     * 사망신고서
     * @param residentSerialNumber 대상자 시리얼번호
     * @return
     */
    public BirthDeathReportResidentDto getDeathCertificate(Integer residentSerialNumber){

        createCertificate(residentSerialNumber,"사망신고서");


        return birthDeathReportResidentRepository
                        .findByBirthDeathReportResidentPk_ResidentSerialNumberAndBirthDeathReportResidentPk_BirthDeathTypeCode
                                (residentSerialNumber,"사망");

    }

    /**
     *
     * @param residentSerialNumber
     * @return 증명서 발급 목록
     */
    public Page<CertificateIssueDto> getCertificateList(Pageable pageable, Integer residentSerialNumber){
        return certificateIssueRepository.getAllByResident_ResidentSerialNumber(pageable, residentSerialNumber);
    }
//    public List<CertificateIssueDto> getCertificateList(Integer residentSerialNumber){
//
//        return certificateIssueRepository.findByResident_ResidentSerialNumber(residentSerialNumber);
//    }

}
