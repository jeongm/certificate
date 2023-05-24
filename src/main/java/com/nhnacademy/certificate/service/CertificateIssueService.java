package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.domain.entitydto.CertificateIssueDto;
import com.nhnacademy.certificate.domain.entitydto.ResidentDto;
import com.nhnacademy.certificate.domain.viewdto.FamilyCertificateDto;
import com.nhnacademy.certificate.domain.viewdto.FamilyResidentDto;
import com.nhnacademy.certificate.domain.viewdto.HouseholdDto;
import com.nhnacademy.certificate.domain.viewdto.ResidentCertificateDto;
import com.nhnacademy.certificate.entity.CertificateIssue;
import com.nhnacademy.certificate.repository.CertificateIssueRepository;
import com.nhnacademy.certificate.repository.HouseholdCompositionResidentRepository;
import com.nhnacademy.certificate.repository.HouseholdMovementAddressRepository;
import com.nhnacademy.certificate.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Transactional
@Service
@RequiredArgsConstructor
public class CertificateIssueService {
    private final CertificateIssueRepository certificateIssueRepository;
    private final ResidentRepository residentRepository;
    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;
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

        // TODO residentSerialNumber를 기준으로 householdserialnumber뽑고
        // TODO query 두번 날려서 세대주 전입 기록, 세대 주민 기록 household에서 리스트로 가져옴


        HouseholdDto household = householdCompositionResidentRepository.findByResident_ResidentSerialNumber(residentSerialNumber);
        if(Objects.isNull(household)){
            return null;
        }
        ResidentCertificateDto residentCertificate = ResidentCertificateDto.builder()
                .issueDate(certificateIssue.getCertificateIssueDate())
                .certificateConfirmationNumber(certificateIssue.getCertificateConfirmationNumber())
                .resident(household.getHousehold().getResident())
                .householdMovementAddresses(household.getHousehold().getHouseholdMovementAddresses())
                .householdResidents(household.getHousehold().getHouseholdCompositionResidents())
                .build();

        return residentCertificate;
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
