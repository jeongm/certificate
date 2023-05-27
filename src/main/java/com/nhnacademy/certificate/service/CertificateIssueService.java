package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.domain.restviewdto.CertificateIssueDto;
import com.nhnacademy.certificate.domain.restviewdto.HouseholdMovementAddressDto;
import com.nhnacademy.certificate.domain.viewdto.*;
import com.nhnacademy.certificate.entity.CertificateIssue;
import com.nhnacademy.certificate.exception.HouseholdNotFoundException;
import com.nhnacademy.certificate.repository.*;
import lombok.RequiredArgsConstructor;
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
     * @param certificateNumber 증명서 번호가 있을 시 가져옴, 그 외에는 발급
     * @return 가족관계증명서
     */
    public FamilyCertificateDto getFamilyCertificate(Integer residentSerialNumber, Long certificateNumber){
        CertificateIssueDto certificateIssue;

        if(Objects.isNull(certificateNumber)){
            certificateIssue = getCertificate(createCertificate(residentSerialNumber,"가족관계증명서"));
        }else{
            certificateIssue = getCertificate(certificateNumber);
        }

        FamilyCertificateDto familyCertificate = FamilyCertificateDto.builder()
                .certificateIssueDate(certificateIssue.getCertificateIssueDate())
                .certificateConfirmationNumber(certificateIssue.getCertificateConfirmationNumber())
                .baseResident((residentRepository.findByResidentSerialNumber(residentSerialNumber)))
                .familyResident(residentRepository.findByFamilyResident(residentSerialNumber))
                .build();

        return familyCertificate;
    }


    /**
     * 주민등록등본 발급
     * @param residentSerialNumber
     * @param certificateNumber 있는 증명서면 가져옴
     * @return
     */
    public ResidentCertificateDto getResidentCertificate(Integer residentSerialNumber, Long certificateNumber){
        CertificateIssueDto certificateIssue;

        if(Objects.isNull(certificateNumber)){
            certificateIssue = getCertificate(createCertificate(residentSerialNumber,"주민등록든본"));
        }else{
            certificateIssue = getCertificate(certificateNumber);
        }

        HouseholdCompositionDto householdComposition = householdCompositionResidentRepository.findByResident_ResidentSerialNumber(residentSerialNumber);
        if(Objects.isNull(householdComposition)){
            throw new HouseholdNotFoundException();
        }
        ResidentCertificateDto residentCertificate = ResidentCertificateDto.builder()
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

        return residentCertificate;
    }

    /**
     * 출생증명서
     * @param residentSerialNumber 대상자 시리얼번호
     * @return
     */
    public BirthCertificateDto getBirthCertificate(Integer residentSerialNumber){

        Long certificateIssueSerialNumber = createCertificate(residentSerialNumber,"출생신고서");

        BirthCertificateDto birthCertificate = BirthCertificateDto.builder()
                .birthReportResident(birthDeathReportResidentRepository
                        .findByBirthDeathReportResidentPk_ResidentSerialNumberAndBirthDeathReportResidentPk_BirthDeathTypeCode
                                (residentSerialNumber,"출생"))
                .father(familyRelationshipRepository.findFamilyResident(residentSerialNumber,"부"))// familyresident- 출생자 기준
                .mother(familyRelationshipRepository.findFamilyResident(residentSerialNumber,"모")) /// familyresident- 출생자 기준
                .build();

        return birthCertificate;
    }


    /**
     * 사망신고서
     * @param residentSerialNumber 대상자 시리얼번호
     * @return
     */
    public BirthDeathReportResidentDto getDeathCertificate(Integer residentSerialNumber){

        Long certificateIssueSerialNumber = createCertificate(residentSerialNumber,"사망신고서");

        BirthDeathReportResidentDto deathCertificate =
                birthDeathReportResidentRepository
                        .findByBirthDeathReportResidentPk_ResidentSerialNumberAndBirthDeathReportResidentPk_BirthDeathTypeCode
                                (residentSerialNumber,"사망");

        return deathCertificate;
    }

    /**
     *
     * @param residentSerialNumber
     * @return 증명서 발급 목록
     */
    public List<CertificateIssueDto> getCertificateList(Integer residentSerialNumber){
        return certificateIssueRepository.findByResident_ResidentSerialNumber(residentSerialNumber);
    }

}
