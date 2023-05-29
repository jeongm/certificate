package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.domain.viewdto.CertificateIssueDto;
import com.nhnacademy.certificate.entity.CertificateIssue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long> {
    CertificateIssueDto findByCertificateConfirmationNumber(Long certificateNumber);

    List<CertificateIssueDto> findByResident_ResidentSerialNumber(Integer residentSerialNumber);


}
