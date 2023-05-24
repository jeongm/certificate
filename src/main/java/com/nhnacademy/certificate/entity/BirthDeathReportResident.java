package com.nhnacademy.certificate.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "birth_death_report_resident")
public class BirthDeathReportResident {

    @EmbeddedId
    private BirthDeathReportResidentPk birthDeathReportResidentPk;
    @Column(name = "birth_death_report_date")
    private LocalDate birthDeathReportDate;
    @Column(name = "birth_report_qualifications_code")
    private String birthReportQualificationsCode;
    @Column(name = "death_report_qualifications_code")
    private String deathReportQualificationsCode;
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "phone_number")
    private String phoneNumber;


    @MapsId("residentSerialNumber")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resident_serial_number")
    private Resident targetResident;

    @MapsId("reportResidentSerialNumber")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_resident_serial_number")
    private Resident reportResident;


    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    @Getter
    @Setter
    public static class BirthDeathReportResidentPk implements Serializable{
        @Column(name = "resident_serial_number")
        private Integer residentSerialNumber;
        @Column(name = "birth_death_type_code")
        private String birthDeathTypeCode;
        @Column(name = "report_resident_serial_number")
        private Integer reportResidentSerialNumber;
    }




}
