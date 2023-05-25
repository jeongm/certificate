package com.nhnacademy.certificate.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "certificate_issue")
public class CertificateIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certificate_confirmation_number")
    private Long certificateConfirmationNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;
    @Column(name = "certificate_type_code")
    private String certificateTypeCode;
    @Column(name = "certificate_issue_date")
    private LocalDate certificateIssueDate;
}
