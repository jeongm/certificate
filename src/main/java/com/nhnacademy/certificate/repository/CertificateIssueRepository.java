package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.domain.viewdto.CertificateIssueDto;
import com.nhnacademy.certificate.domain.viewdto.ResidentDto;
import com.nhnacademy.certificate.entity.CertificateIssue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long> {
    CertificateIssueDto findByCertificateConfirmationNumber(Long certificateNumber);
    Page<CertificateIssueDto> getAllByResident_ResidentSerialNumber(Pageable pageable, Integer residentSerialNumber);

//    List<CertificateIssueDto> findByResident_ResidentSerialNumber(Integer residentSerialNumber);


}
