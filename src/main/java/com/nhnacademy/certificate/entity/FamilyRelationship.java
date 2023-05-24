package com.nhnacademy.certificate.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "family_relationship")
public class FamilyRelationship {

    @EmbeddedId
    private FamilyRelationshipPk familyRelationshipPk;

    @Column(name = "family_relationship_code")
    private String familyRelationshipCode;

    @MapsId("baseResidentSerialNumber")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_resident_serial_number")
    private Resident baseResident;

    @MapsId("familyResidentSerialNumber")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_resident_serial_number")
    private Resident targetResident;


    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    @Getter
    public static class FamilyRelationshipPk implements Serializable{
        @Column(name = "base_resident_serial_number")
        private Integer baseResidentSerialNumber;
        @Column(name = "family_resident_serial_number")
        private Integer familyResidentSerialNumber;
    }
}
