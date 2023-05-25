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
@Table(name = "household_movement_address")
public class HouseholdMovementAddress {
    @EmbeddedId
    private HouseholdMovementAddressPk householdMovementAddressPk;

    @Column(name = "house_movement_address")
    private String houseMovementAddress;
    @Column(name = "last_address_yn")
    private String lastAddressYn;

    @MapsId("householdSerialNumber")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_serial_number")
    private Household household;

    @Getter
    @Setter
    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HouseholdMovementAddressPk implements Serializable {
        @Column(name = "household_serial_number")
        private Integer householdSerialNumber;

        @Column(name = "house_movement_report_date")
        private LocalDate houseMovementReportDate;

    }

}
