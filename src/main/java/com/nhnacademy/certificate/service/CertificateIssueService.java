package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.domain.entitydto.CertificateIssueDto;
import com.nhnacademy.certificate.domain.entitydto.ResidentDto;
import com.nhnacademy.certificate.domain.viewdto.FamilyCertificateDto;
import com.nhnacademy.certificate.domain.viewdto.FamilyResidentDto;
import com.nhnacademy.certificate.domain.viewdto.ResidentCertificateDto;
import com.nhnacademy.certificate.entity.CertificateIssue;
import com.nhnacademy.certificate.repository.CertificateIssueRepository;
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
    private final HouseholdMovementAddressRepository householdMovementAddressRepository;

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



    public ResidentCertificateDto getResidentCertificate(Integer residentSerialNumber, Long certificateNumber){
        CertificateIssueDto certificateIssue;

        if(Objects.isNull(certificateNumber)){
            certificateIssue = getCertificate(createCertificate(residentSerialNumber,"주민등록든본"));
        }else{
            certificateIssue = getCertificate(certificateNumber);
        }

        ResidentCertificateDto residentCertificate = ResidentCertificateDto.builder()
                .issueDate(certificateIssue.getCertificateIssueDate())
                .certificateConfirmationNumber(certificateIssue.getCertificateConfirmationNumber())
                .householdMovementAddresses(householdMovementAddressRepository.findHouseholdMovementAddresses(5))
                .build();

        /**
         * 세대주 성명
         * 세대구성사유및일자
         *
         */

        // 주민에서 세대 가져올까? in?

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
