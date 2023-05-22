package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.domain.entitydto.CertificateIssueDto;
import com.nhnacademy.certificate.domain.responsedto.FamilyCertificateDto;
import com.nhnacademy.certificate.domain.responsedto.ResidentCertificateDto;
import com.nhnacademy.certificate.entity.CertificateIssue;
import com.nhnacademy.certificate.repository.CertificateIssueRepository;
import com.nhnacademy.certificate.repository.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Transactional
@Service
public class CertificateIssueService {
    private final CertificateIssueRepository certificateIssueRepository;
    private final ResidentRepository residentRepository;

    public CertificateIssueService(CertificateIssueRepository certificateIssueRepository, ResidentRepository residentRepository) {
        this.certificateIssueRepository = certificateIssueRepository;
        this.residentRepository = residentRepository;
    }

    public Long createCertificate(Integer residentSerialNumber, String certificateType){
        CertificateIssue certificateIssue = CertificateIssue.builder()
                .resident(residentRepository.getReferenceById(residentSerialNumber))
                .certificateTypeCode(certificateType) // parse
                .certificateIssueDate(LocalDate.now())
                .build();
        certificateIssueRepository.save(certificateIssue);
        return certificateIssue.getCertificateConfirmationNumber();
    }

    public CertificateIssueDto getCertificate(Long certificateIssueNumber){
        return certificateIssueRepository.findByCertificateConfirmationNumber(certificateIssueNumber);
    }

    public FamilyCertificateDto getFamilyCertificate(Integer residentSerialNumber, Long certificateNumber){
        CertificateIssueDto certificateIssue;

        if(Objects.isNull(certificateNumber)){
            certificateIssue = getCertificate(createCertificate(residentSerialNumber,"가족관계증명서"));
        }else{
            certificateIssue = getCertificate(certificateNumber);
        }

        FamilyCertificateDto familyCertificate = FamilyCertificateDto.builder()
                .baseResident(residentRepository.findByResidentSerialNumber(residentSerialNumber))
                .issueDate(certificateIssue.getCertificateIssueDate())
                .certificateConfirmationNumber(certificateIssue.getCertificateConfirmationNumber())
                .registrationBaseAddress(residentRepository.findByResidentSerialNumber(residentSerialNumber).getRegistrationBaseAddress())
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
                .build();

        /**
         * 세대주 성명
         * 세대구성사유및일자
         *
         */

        return residentCertificate;
    }






    public List<CertificateIssueDto> getCertificateList(Integer residentSerialNumber){
        return certificateIssueRepository.findByResident_ResidentSerialNumber(residentSerialNumber);
    }

}
