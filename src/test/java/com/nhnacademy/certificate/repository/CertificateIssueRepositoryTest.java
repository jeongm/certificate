package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.config.RootConfig;
import com.nhnacademy.certificate.config.WebConfig;
import com.nhnacademy.certificate.domain.entitydto.CertificateIssueDto;
import com.nhnacademy.certificate.domain.viewdto.FamilyCertificateDto;
import com.nhnacademy.certificate.service.CertificateIssueService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Transactional
@WebAppConfiguration
@ContextHierarchy(value = {
        @ContextConfiguration(classes = {RootConfig.class}),
        @ContextConfiguration(classes = {WebConfig.class})
})
class CertificateIssueRepositoryTest {
    @Autowired
    CertificateIssueService certificateService;

    @Autowired
    ResidentRepository residentRepository;
    @Autowired
    CertificateIssueRepository certificateIssueRepository;
    @Test
    void findByCertificateConfirmationNumberAndResident_ResidentSerialNumber() {
        CertificateIssueDto certificateIssue = certificateService.getCertificate(certificateService.createCertificate(2,"가족관계증명서"));
        assertThat(certificateIssue.getResident().getResidentSerialNumber()).isEqualTo(2);
//        FamilyCertificateDto familyCertificate = FamilyCertificateDto.builder()
//                .baseResident(residentRepository.findByResidentSerialNumber(1))
//                .certificateIssue(certificateIssue)
//                .familyResident(residentRepository.findByFamilyResident(1))
//                .build();
//        assertThat(familyCertificate.getBaseResident().getRegistrationBaseAddress()).isEqualTo("e");

    }

    @Test
    void findByCertificateConfirmationNumberAndResident_ResidentSerialNumber2() {
        CertificateIssueDto certificateIssue = certificateService.getCertificate(certificateService.createCertificate(1,"가족관계증명서"));

//        FamilyCertificateDto familyCertificate = certificateIssueRepository.findByResident_ResidentSerialNumberAndCertificateConfirmationNumber(1, certificateIssue.getCertificateConfirmationNumber());

//        assertThat(familyCertificate.getBaseResident().getGenderCode()).isEqualTo("e");
//        assertThat(familyCertificate.getTargetResident().get(0).).isEqualTo("e");
//        assertThat(residentRepository.findByFamilyResident(1).get(0).getGenderCode()).isEqualTo("d");


    }
}