package com.nhnacademy.certificate.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "resident")
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resident_serial_number")
    private Integer residentSerialNumber;
    private String name;
    @Column(name = "resident_registration_number")
    private String residentRegistrationNumber;
    @Column(name = "gender_code")
    private String genderCode;
    @Column(name = "birth_date")
    private LocalDateTime birthDate;
    @Column(name = "birth_place_code")
    private String birthPlaceCode;
    @Column(name = "registration_base_address")
    private String registrationBaseAddress;
    @Column(name = "death_date")
    private LocalDateTime deathDate;
    @Column(name = "death_place_code")
    private String deathPlaceCode;
    @Column(name = "death_place_address")
    private String deathPlaceAddress;

    @OneToMany(mappedBy = "targetResident")
    private List<BirthDeathReportResident> birthDeathReportResidents;

    @OneToMany(mappedBy = "targetResident")
    private List<FamilyRelationship> targetFamilyRelationships;

//    @OneToMany(mappedBy = "resident")
//    private List<Household> households;

    @OneToMany(mappedBy = "resident")
    List<CertificateIssue> certificateIssueList;

}
